package app.coconut2.coconut2_mvvm.interfaces

import okhttp3.Interceptor

interface IApiService {
    fun init(
        apiDomain: String,
        allowUntrusted: Boolean,
        clazz: Class<*>,
        timeout: Long,
        enableLoggingHttp: Boolean,
        interceptors: Array<Interceptor>,
        networkInterceptors: Array<Interceptor>
    ): Any
}