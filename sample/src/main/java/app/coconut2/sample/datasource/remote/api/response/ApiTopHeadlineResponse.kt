package app.coconut2.sample.datasource.remote.api.response

import app.coconut2.coconut2_mvvm.base.BaseResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

class ApiTopHeadlineResponse : BaseResponse() {
    val sources: List<DataResponse?> = emptyList()

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class DataResponse(
        val id: String,
        val name: String,
        val description: String,
        val url: String,
        val category: String,
        val language: String,
        val country: String,
    )
}
