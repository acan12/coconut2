package app.coconut2.coconut2_mvvm.network

sealed class ApiState<out T> {
    data class Success<out T>(val data: T) : ApiState<T>()
    data class Error<S>(val message: String?, val data: S? = null): ApiState<Nothing>()
    class Loading<T> : ApiState<T>()
}