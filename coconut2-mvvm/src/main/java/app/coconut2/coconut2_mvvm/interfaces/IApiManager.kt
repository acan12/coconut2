package app.coconut2.coconut2_mvvm.interfaces

import okhttp3.Interceptor
import retrofit2.Retrofit

interface IApiManager {
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