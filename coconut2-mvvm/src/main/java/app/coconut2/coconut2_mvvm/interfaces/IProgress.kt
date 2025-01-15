package app.coconut2.coconut2_mvvm.interfaces

import android.content.Context

interface IProgress {

    fun showProgressDialog(context: Context?, message: String?, isCanceledOnTouch: Boolean)
}