package app.coconut2.coconut2_mvvm.base.domain

import app.coconut2.coconut2_mvvm.base.datasource.remote.BaseResponse
import app.coconut2.coconut2_mvvm.base.datasource.remote.ErrorResponse
import app.coconut2.coconut2_mvvm.interfaces.IApiResponse

class BaseRepository : IApiResponse{
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