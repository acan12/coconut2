package app.coconut2.sample.datasource.remote.api

import app.coconut2.sample.BuildConfig
import app.coconut2.sample.datasource.remote.api.request.TopHeadlineRequest
import app.coconut2.sample.datasource.remote.api.response.ApiTopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import kotlin.jvm.Throws

interface DataApi {

    @GET("top-headlines/sources?apiKey=${BuildConfig.NEWSORG_APIKEY}")
    @Throws(Exception::class)
    suspend fun getTopHeadlines(@QueryMap request: TopHeadlineRequest): ApiTopHeadlineResponse

}