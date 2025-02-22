package app.coconut2.sample.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.sample.BuildConfig
import app.coconut2.sample.R
import app.coconut2.sample.data.model.entity.UserEntity
import app.coconut2.sample.databinding.ActivityMainBinding
import app.coconut2.sample.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: UserViewModel by viewModels()

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.textTitlePage.text = resources.getString(R.string.app_name)
        
        binding.btnInsertData.setOnClickListener {
            viewModel.insert(
                UserEntity(
                    name = "Dodol",
                    address = "Tanah Kusir",
                    age = 18
                )
            )
            Toast.makeText(this, "Data Inserted!", Toast.LENGTH_LONG).show()
        }


    }
}