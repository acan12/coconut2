package app.coconut2.coconut2_mvvm.base.ui.ext

import android.view.View

fun View?.avoidDoubleClicks() {
    this ?: return
    if (!this.isClickable) {
        return
    }
    this.isClickable = false
    this.postDelayed({ this.isClickable = true }, 1000L)
}