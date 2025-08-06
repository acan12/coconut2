package app.coconut2.sample.data.remote.source.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

abstract class BaseResponse<T> (
    val meta: BaseMeta = BaseMeta(),
    val data: T? = null
){
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class BaseMeta(
        var status: Boolean = false,
        var code: Int = 0,
        var message: String = ""
    )
}