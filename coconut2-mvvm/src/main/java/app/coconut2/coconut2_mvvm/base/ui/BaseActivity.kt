package app.coconut2.coconut2_mvvm.base.ui

import android.content.ComponentCallbacks2
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), ComponentCallbacks2 {
    val currentActivity: BaseActivity
        get() = this
}