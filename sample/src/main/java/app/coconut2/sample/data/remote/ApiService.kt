package app.coconut2.sample.data.remote

import app.coconut2.sample.BuildConfig
import app.coconut2.sample.data.remote.response.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface ApiService {

    @GET("top-headlines/sources?apiKey=${BuildConfig.NEWSORG_APIKEY}")
    suspend fun getTopHeadlines(@HeaderMap header: Map<String, String>): TopHeadlineResponse

}