package app.coconut2.coconut2_mvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.coconut2.coconut2_mvvm.core.ui.UIAction
import app.coconut2.coconut2_mvvm.core.ui.UIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<S: UIState> : ViewModel(){
    private val _uiState = MutableStateFlow<S?>(null)
    val uiState: StateFlow<S?> = _uiState

    fun setState(state: S){
        _uiState.value = state
    }
}