package app.coconut2.sample.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.ComponentCaller
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.sample.R
import app.coconut2.sample.databinding.ActivityMainBinding
import app.coconut2.sample.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val CAMERA_PERMISSION_CODE = 100
    private var recording: Recording? = null

    private val userViewModel: UserViewModel by viewModels()

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.textTitlePage.text = resources.getString(R.string.app_name)
        binding.btnRecordVideo.setOnClickListener {
            if(hasRequiredPermission()) {
                val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 200)
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0)
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_PERMISSION_CODE);
                }
            } else {
                ActivityCompat.requestPermissions(this, CAMERAX_PERMISSIONS, CAMERA_PERMISSION_CODE)
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
    }

//    @SuppressLint("MissingPermission")
//    private fun recordVideo(controller: LifecycleCameraController) {
//        if (recording != null) {
//            recording?.stop()
//            recording = null
//            return
//        }
//
//        if (!hasRequiredPermission()) {
//            ActivityCompat.requestPermissions(this, CAMERAX_PERMISSIONS, CAMERA_PERMISSION_CODE)
//        } else {
//            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT).show()
//            val outputFile = File(filesDir, "my-recording.mp4")
//            recording = controller.startRecording(
//                FileOutputOptions.Builder(outputFile).build(),
//                AudioConfig.create(true),
//                ContextCompat.getMainExecutor(applicationContext),
//            ) { event ->
//                when (event) {
////                    is VideoRecordEvent.Finalize -> {
////                        if (event.hasError()) {
////                            recording?.close()
////                            recording = null
////
////                            Toast.makeText(
////                                applicationContext,
////                                "Video capture failed",
////                                Toast.LENGTH_LONG
////                            ).show()
////                        } else {
////                            Toast.makeText(
////                                applicationContext,
////                                "Video capture succeeded",
////                                Toast.LENGTH_LONG
////                            ).show()
////                        }
////                    }
//                }
//            }
//        }
//    }


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

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray,
//        deviceId: Int
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
//
//        if(requestCode == CAMERA_PERMISSION_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this@MainActivity, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this@MainActivity, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}