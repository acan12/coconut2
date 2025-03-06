package app.coconut2.sample.data.remote

import app.coconut2.sample.BuildConfig
import app.coconut2.sample.data.remote.response.TopHeadlineResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import kotlin.jvm.Throws

interface ApiService {

    @GET("top-headlines/sources?apiKey=${BuildConfig.NEWSORG_APIKEY}")
    @Throws(Exception::class)
    suspend fun getTopHeadlines(@HeaderMap header: Map<String, String>): Flow<TopHeadlineResponse>

}