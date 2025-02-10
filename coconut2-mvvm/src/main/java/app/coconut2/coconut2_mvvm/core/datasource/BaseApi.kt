package app.coconut2.coconut2_mvvm.core.datasource

import app.coconut2.coconut2_mvvm.interfaces.IApiService
import app.coconut2.coconut2_mvvm.network.NetworkResult
import okhttp3.Interceptor
import okhttp3.Response

open class BaseApi() {
    enum class Method {
        GET, PUT, POST, UPDATE, DELETE
    }

    protected fun setupApiDomain(
        apiService: IApiService,
        apiDomain: String,
        allowUntrusted: Boolean,
        apiservice: Class<*>,
        timeOut: Long,
        enableLogging: Boolean,
        interceptors: Array<Interceptor> = arrayOf(),
        networkInterceptors: Array<Interceptor> = arrayOf()
    ): Any {
        return apiService.initApiService(
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