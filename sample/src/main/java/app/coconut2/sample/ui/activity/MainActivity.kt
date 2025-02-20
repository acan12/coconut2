package app.coconut2.sample.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.sample.R
import app.coconut2.sample.databinding.ActivityMainBinding
import app.coconut2.sample.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val CAMERA_PERMISSION_CODE = 100

    //    private lateinit var previewView: PreviewView
    private lateinit var recordButton: Button
    private var service: ExecutorService? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null

    private val userViewModel: UserViewModel by viewModels()

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (hasRequiredPermission()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, CAMERAX_PERMISSIONS, CAMERA_PERMISSION_CODE)
        }

        binding.recordButton.setOnClickListener {
            if (recording != null) {
                stopRecording()
            } else {
                startRecording()
            }
        }

        service = Executors.newSingleThreadExecutor();

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                startCamera()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            // Set up the preview use case
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.previewView.surfaceProvider)
            }

            // Set up the video capture use case
            val recorder = Recorder.Builder()
                .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
                .build()
            videoCapture = VideoCapture.withOutput(recorder)

            // Select the back camera as the default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind all use cases before rebinding
                cameraProvider.unbindAll()

                // Bind the preview and video capture use cases to the camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, videoCapture
                )
            } catch (exc: Exception) {
                Log.e("CameraX", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @SuppressLint("MissingPermission")
    private fun startRecording() {
        binding.recordButton.setImageResource(R.drawable.round_stop_circle_24)
//        if(recording != null){
//            recording?.stop()
//            recording = null
//            return
//        }

//        val videoFile = File(
//            externalMediaDirs.firstOrNull(),
//            "${System.currentTimeMillis()}.mp4"
//        )
//
//        val outputOptions = FileOutputOptions.Builder(videoFile).build()
        val name: String = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.getDefault()).format(
            System.currentTimeMillis()
        )
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        contentValues.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CameraX-Video")

        val options = MediaStoreOutputOptions.Builder(
            contentResolver,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
            .setContentValues(contentValues).build()


        recording = videoCapture?.output
            ?.prepareRecording(this, options)
            ?.withAudioEnabled()
            ?.start(ContextCompat.getMainExecutor(this)) { recordEvent ->
                when (recordEvent) {
                    is VideoRecordEvent.Start -> {
                        recordButton.text = "Stop Recording"
                    }

                    is VideoRecordEvent.Finalize -> {
                        if (!recordEvent.hasError()) {
                            val msg = "Video saved: ${recordEvent.outputResults.outputUri}"
                            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        } else {
                            recording?.close()
                            recording = null
                            Log.e("CameraX", "Video capture failed: ${recordEvent.error}")
                        }
                        recordButton.text = "Start Recording"
                    }
                }
            }
    }

    private fun stopRecording() {
        recording?.stop()
        recording = null
    }


    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private fun hasRequiredPermission(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service?.shutdown()
    }
}