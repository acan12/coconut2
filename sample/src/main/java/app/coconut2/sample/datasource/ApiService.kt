package app.coconut2.sample.datasource

import app.coconut2.sample.BuildConfig
import app.coconut2.sample.datasource.remote.request.TopHeadlineRequest
import app.coconut2.sample.datasource.remote.response.ApiTopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("top-headlines/sources?apiKey=${BuildConfig.NEWSORG_APIKEY}")
    suspend fun getTopHeadlines(@QueryMap request: TopHeadlineRequest): ApiTopHeadlineResponse

}