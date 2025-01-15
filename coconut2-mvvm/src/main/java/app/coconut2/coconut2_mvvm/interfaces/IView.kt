package app.coconut2.coconut2_mvvm.interfaces

import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.coconut2_mvvm.base.remote.BaseResponse
import app.coconut2.coconut2_mvvm.base.remote.ErrorResponse

interface IView {
    val currentActivity: BaseActivity

    fun handleSuccess(response: BaseResponse?)
    fun handleError(message: String?)
    fun handleError(response: ErrorResponse?)

    fun handleNoConnectionInternet()
    fun callbackReConnectingNetwork()
}