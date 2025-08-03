package app.coconut2.sample.ui.screen.activity

import android.os.Bundle
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.sample.databinding.ActivityDemoMainBinding

class DemoMainActivity : BaseActivity<ActivityDemoMainBinding>() {

    override fun inflateBinding(): ActivityDemoMainBinding =
        ActivityDemoMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {

    }
}