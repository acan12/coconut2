package app.coconut2.sample.data.remote.source.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TopHeadlineResponse(
    val sources: List<DataResponse> = emptyList()
): BaseResponse<DataResponse>()

@JsonIgnoreProperties(ignoreUnknown = true)
class DataResponse {
    val name: String? = null
    val description: String? = null
    val url: String? = null
}
