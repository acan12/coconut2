package app.coconut2.coconut2_mvvm.network

import app.coconut2.coconut2_mvvm.core.datasource.helper.UnsafeHttpClientHelper
import app.coconut2.coconut2_mvvm.interfaces.IApiManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit

class ApiManager : IApiManager {

    override fun init(
        apiDomain: String,
        allowUntrusted: Boolean,
        clazz: Class<*>,
        timeout: Long,
        enableLoggingHttp: Boolean,
        interceptors: Array<Interceptor>,
        networkInterceptors: Array<Interceptor>,
        converterType: Converter.Factory,
    ): Any {

        return Retrofit.Builder()
            .baseUrl(apiDomain)
            .addConverterFactory(converterType)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(
                getHttpClient(
                    allowUntrusted,
                    timeout,
                    enableLoggingHttp,
                    interceptors,
                    networkInterceptors
                )
            ).build().create(clazz)
    }

    private fun getHttpClient(
        allowUntrustedSSL: Boolean,
        timeout: Long,
        enableLoggingHttp: Boolean,
        customInterceptors: Array<Interceptor>,
        customNetworkInterceptors: Array<Interceptor>,
    ): OkHttpClient {
        var httpClient = OkHttpClient.Builder()

        // allowUntrustedSSL: true , if activate Untrusted SSL
        if (allowUntrustedSSL) httpClient = UnsafeHttpClientHelper.getUnsafeOkHttpClient()
        httpClient.connectTimeout(timeout, TimeUnit.SECONDS)
        httpClient.readTimeout(timeout, TimeUnit.SECONDS)

        // Interceptor logging HTTP request
        if (enableLoggingHttp) {
            var logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }

        // Pre-Interceptor
        executePreInterceptor(httpClient, customInterceptors)

        // Post-Network Interceptor
        executeNetworkInterceptor(httpClient, customNetworkInterceptors)
        return httpClient.build()
    }

    private fun executePreInterceptor(
        httpClient: OkHttpClient.Builder,
        customInterceptors: Array<Interceptor>
    ) {

        // add custom interceptor
        if (customInterceptors.isNotEmpty())
            for (interceptor: Interceptor in customInterceptors)
                httpClient.addInterceptor(interceptor)
    }

    private fun executeNetworkInterceptor(
        httpClient: OkHttpClient.Builder,
        customNetworkInterceptors: Array<Interceptor>
    ) {
        // add network interceptor
        if (customNetworkInterceptors.isNotEmpty())
            for (interceptor: Interceptor in customNetworkInterceptors)
                httpClient.addNetworkInterceptor(interceptor)
    }
}