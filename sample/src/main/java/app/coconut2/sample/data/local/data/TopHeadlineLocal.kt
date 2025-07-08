package app.coconut2.sample.data.local.data

import android.content.SharedPreferences
import app.coconut2.coconut2_mvvm.core.datasource.local.cache.BaseLocalPreference
import app.coconut2.sample.data.remote.response.TopHeadlineResponse
import com.fasterxml.jackson.databind.ObjectMapper

class TopHeadlineLocal(
    sharedPreferences: SharedPreferences
) : BaseLocalPreference<TopHeadlineResponse>(sharedPreferences) {
    override fun getKeyName(): String = "top_headline_response"

    override fun getValue(json: String): TopHeadlineResponse =
        ObjectMapper().readValue(json, TopHeadlineResponse::class.java)

}