package app.coconut2.coconut2_mvvm.base.ui

import android.content.ComponentCallbacks2
import androidx.appcompat.app.AppCompatActivity
import app.coconut2.coconut2_mvvm.base.remote.BaseResponse
import app.coconut2.coconut2_mvvm.base.remote.ErrorResponse
import app.coconut2.coconut2_mvvm.interfaces.IApiResponse

open class BaseActivity : AppCompatActivity(), IApiResponse, ComponentCallbacks2 {
    override fun handleSuccess(response: BaseResponse?) {
        TODO("Not yet implemented")
    }

    override fun handleError(message: String?) {
        TODO("Not yet implemented")
    }

    override fun handleError(response: ErrorResponse?) {
        TODO("Not yet implemented")
    }
}



//    override val currentActivity: BaseActivity
//        get() = this
//
//    override fun handleSuccess(response: BaseResponse?) {}
//    override fun handleError(message: String?) {}
//    override fun handleError(response: ErrorResponse?) {}
//    override fun handleNoConnectionInternet() {}
//    override fun callbackReConnectingNetwork() {}
