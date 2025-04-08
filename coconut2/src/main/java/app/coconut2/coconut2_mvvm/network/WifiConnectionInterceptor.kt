package app.coconut2.coconut2_mvvm.network

import app.coconut2.coconut2_mvvm.exception.NetworkLostConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class WifiConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!WifiConnectionService.getInstance().isConnected()){
            throw NetworkLostConnectionException()
        } else {
            return chain.proceed(chain.request())
        }
    }
}