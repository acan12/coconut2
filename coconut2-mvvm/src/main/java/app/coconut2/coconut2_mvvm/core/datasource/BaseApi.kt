package app.coconut2.coconut2_mvvm.core.datasource

import app.coconut2.coconut2_mvvm.interfaces.IApiService
import app.coconut2.coconut2_mvvm.network.NetworkResult
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit

sealed class BaseApi() {
    enum class Method {
        GET, PUT, POST, UPDATE, DELETE
    }

    protected fun builder(
        apiService: IApiService,
        apiDomain: String,
        allowUntrusted: Boolean,
        apiservice: Class<*>,
        timeOut: Long,
        enableLogging: Boolean,
        interceptors: Array<Interceptor> = arrayOf(),
        networkInterceptors: Array<Interceptor> = arrayOf()
    ): Any {
        return apiService.init(
            apiDomain,
            allowUntrusted,
            apiservice,
            timeOut,
            enableLogging,
            interceptors,
            networkInterceptors
        )
    }
}