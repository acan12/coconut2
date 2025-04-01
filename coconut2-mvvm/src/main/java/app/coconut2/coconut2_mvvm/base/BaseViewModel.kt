//package app.coconut2.coconut2_mvvm.base
//
//import androidx.lifecycle.ViewModel
//import app.coconut2.coconut2_mvvm.core.ui.NetworkState
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//
//open class BaseViewModel<S: NetworkState> : ViewModel(){
//    private val _uiState = MutableStateFlow<S?>(null)
//    val uiState: StateFlow<S?> = _uiState
//
//    fun setState(state: S){
//        _uiState.value = state
//    }
//}