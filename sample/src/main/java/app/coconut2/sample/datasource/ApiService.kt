package app.coconut2.sample.datasource

import app.coconut2.sample.datasource.remote.request.TopHeadlineRequest
import app.coconut2.sample.datasource.remote.response.ApiTopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("top-headlines/sources?apiKey=6d362365d5e245faa1fe3253c83c45ac")
    suspend fun getTopHeadlines(@QueryMap request: TopHeadlineRequest): ApiTopHeadlineResponse

}