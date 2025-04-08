package app.coconut2.coconut2_mvvm.core.datasource.interfaces

import okhttp3.Interceptor
import retrofit2.Retrofit

interface IApiService {
    fun initApiService(
        apiDomain: String,
        allowUntrusted: Boolean,
        clazz: Class<*>,
        timeout: Long,
        enableLoggingHttp: Boolean,
        interceptors: Array<Interceptor>,
        networkInterceptors: Array<Interceptor>
    ): Retrofit
}