package app.coconut2.coconut2_mvvm.base

import app.coconut2.coconut2_mvvm.core.datasource.remote.ErrorResponse
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