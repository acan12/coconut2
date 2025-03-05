package app.coconut2.coconut2_mvvm.core.ui

sealed class NetworkState {
    object Loading : NetworkState()
    data class Success<T>(val data : T) : NetworkState()
    data class Error(val message: String) : NetworkState()
}