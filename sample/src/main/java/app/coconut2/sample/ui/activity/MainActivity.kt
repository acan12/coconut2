package app.coconut2.sample.ui.activity

import android.os.Bundle
import androidx.core.view.WindowCompat
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.sample.BuildConfig
import app.coconut2.sample.R
import app.coconut2.sample.databinding.ActivityMainBinding
import app.coconut2.sample.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {

    private val userViewModel: UserViewModel by viewModels()

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.textTitlePage.text = resources.getString(R.string.app_name)
        val x = BuildConfig.SERVER_URL
    }
}