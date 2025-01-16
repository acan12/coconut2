package app.coconut2.coconut2_mvvm.network


class NetworkErrorParser {

//    inline fun <reified T> parseErrorThrowable(jsonErrorBody: String): T? {
//        try {
////            val error: HttpException = (e as HttpException)
//            var message = "Error network connection"
//            if (!jsonErrorBody.isNullOrBlank()) {
//                var jsonResponse = "{\"meta\":{\"status\":false,\"code\":${error.code()},\"message\":\"${message}\"},\"data\":null}"
//
//                var objMapper = ObjectMapper().readValue(jsonErrorBody, T::class.java)
////                error.response()?.let { response ->
////                    response.errorBody()?.let { responseBody ->
////                        jsonResponse = responseBody.string()
////                        objMapper = ObjectMapper().readValue(jsonResponse, T::class.java)
////                    }
////                }
//                return objMapper
//            } else return null
//        } catch (e: Exception) {
//            return null
//        }
//    }

}