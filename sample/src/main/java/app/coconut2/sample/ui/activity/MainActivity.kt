package app.coconut2.sample.ui.activity

import android.os.Bundle
import androidx.core.view.WindowCompat
import app.coconut2.coconut2_mvvm.base.ui.BaseActivity
import app.coconut2.sample.R
import app.coconut2.sample.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding> () {

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.textTitlePage.text = resources.getString(R.string.app_name)
    }
}