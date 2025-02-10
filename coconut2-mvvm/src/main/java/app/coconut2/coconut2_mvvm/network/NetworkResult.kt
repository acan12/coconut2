package app.coconut2.coconut2_mvvm.network

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null)
    class Loading<T> : NetworkResult<T>()
}