package app.coconut2.sample.ui.activity

import android.os.Bundle
import androidx.core.view.WindowCompat
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.coconut2_mvvm.core.datasource.local.AppDatabase
import app.coconut2.sample.BuildConfig
import app.coconut2.sample.R
import app.coconut2.sample.databinding.ActivityMainBinding
import app.coconut2.sample.datasource.local.SampleDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {
    @Inject
    lateinit var db: SampleDatabase

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.textTitlePage.text = resources.getString(R.string.app_name)
        val x = BuildConfig.SERVER_URL
        val userDao = db.userDao()
    }
}