package app.coconut2.coconut2_mvvm.core.datasource

import app.coconut2.coconut2_mvvm.interfaces.IApiManager
import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory

open class ApiBuilder {

    companion object {
        fun build(
            apiManager: IApiManager,
            apiDomain: String,
            allowUntrusted: Boolean,
            apiService: Class<*>,
            timeOut: Long,
            enableLogging: Boolean,
            interceptors: Array<Interceptor> = arrayOf(),
            networkInterceptors: Array<Interceptor> = arrayOf(),
            converterType: Converter.Factory = JacksonConverterFactory.create()
        ): Any {
            return apiManager.init(
                apiDomain,
                allowUntrusted,
                apiService,
                timeOut,
                enableLogging,
                interceptors,
                networkInterceptors,
                converterType = converterType,
            )
        }
    }
}