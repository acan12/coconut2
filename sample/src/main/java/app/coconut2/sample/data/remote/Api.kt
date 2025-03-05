package app.coconut2.sample.data.remote

import app.coconut2.coconut2_mvvm.core.datasource.ApiBuilder
import app.coconut2.coconut2_mvvm.interfaces.IApiManager
import app.coconut2.sample.BuildConfig
import javax.inject.Inject

open class Api @Inject constructor(val apiManager: IApiManager) {

    fun initHeader(): Map<String, String> {
        val map = HashMap<String, String>()
        map["Cache-Control"] = "no-store"
        map["Content-Type"] = "application/json"
        return map
    }

    fun getDomainNetwork(): ApiService =
        ApiBuilder.build(
            apiDomain = BuildConfig.SERVER_URL,
            allowUntrusted = true,
            apiService = ApiService::class.java,
            timeOut = 300,
            enableLogging = BuildConfig.DEBUG,
            apiManager = apiManager
        ) as ApiService
}