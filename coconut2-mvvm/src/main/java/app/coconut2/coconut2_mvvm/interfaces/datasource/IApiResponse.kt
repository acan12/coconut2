package app.coconut2.coconut2_mvvm.interfaces.datasource

import app.coconut2.coconut2_mvvm.base.remote.BaseResponse
import app.coconut2.coconut2_mvvm.base.remote.ErrorResponse

interface IApiResponse {
    fun handleSuccess(response: BaseResponse?)
    fun handleError(message: String?)
    fun handleError(response: ErrorResponse?)
}