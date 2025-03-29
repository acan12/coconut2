package app.coconut2.sample.domain.mapper

import app.coconut2.sample.data.remote.response.DataResponse

fun DataResponse.toHeadlineData(): HeadlineData =
    HeadlineData(
        name = name.toString(),
        description = description.toString(),
        url = url.toString(),
    )


data class HeadlineData(
    val name: String,
    val description: String,
    val url: String,
)