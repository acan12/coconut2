package app.coconut2.coconut2_mvvm.network

import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.HttpException


class NetworkErrorParser {
    inline fun <reified T> parseErrorThrowable(jsonErrorBody: String, error: HttpException): T? {
        try {
            var message = "Error network connection"
            if (!jsonErrorBody.isNullOrBlank()) {
                var jsonResponse =
                    "dsdasdsa" //"{\"meta\":{\"status\":false,\"code\":${error.code()},\"message\":\"${message}\"},\"data\":null}"

                var objMapper = ObjectMapper().readValue(jsonErrorBody, T::class.java)
                error.response()?.let { response ->
                    response.errorBody()?.let { responseBody ->
                        jsonResponse = responseBody.string()
                        objMapper = ObjectMapper().readValue(jsonResponse, T::class.java)
                    }
                }
                return objMapper
            } else return null
        } catch (e: Exception) {
            return null
        }
    }
}