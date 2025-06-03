package app.coconut2.sample.ui.screen.activity

import android.os.Bundle
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.sample.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun inflateBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}