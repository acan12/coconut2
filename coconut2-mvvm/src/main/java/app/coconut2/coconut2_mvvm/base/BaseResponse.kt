package app.coconut2.coconut2_mvvm.base

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class BaseResponse {
    val meta: BaseMeta = BaseMeta()

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class BaseMeta(
        var status: Boolean = false,
        var code: Int = 0,
        var message: String = ""
    )
}