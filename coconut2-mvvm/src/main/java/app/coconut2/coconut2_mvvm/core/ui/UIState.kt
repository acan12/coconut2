package app.coconut2.coconut2_mvvm.core.ui

sealed class UIState {
    object Loading : UIState()
    data class Success<T>(val data : T) : UIState()
    data class Error(val message: String) : UIState()
}