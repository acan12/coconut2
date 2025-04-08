package app.coconut2.coconut2_mvvm.core.datasource

import app.coconut2.coconut2_mvvm.interfaces.IApiManager
import okhttp3.Interceptor

open class ApiBuilder {
    enum class Method {
        GET, PUT, POST, UPDATE, DELETE
    }

    companion object {
        fun build(
            apiManager: IApiManager,
            apiDomain: String,
            allowUntrusted: Boolean,
            apiService: Class<*>,
            timeOut: Long,
            enableLogging: Boolean,
            interceptors: Array<Interceptor> = arrayOf(),
            networkInterceptors: Array<Interceptor> = arrayOf()
        ): Any {
            return apiManager.init(
                apiDomain,
                allowUntrusted,
                apiService,
                timeOut,
                enableLogging,
                interceptors,
                networkInterceptors
            )
        }
    }
}