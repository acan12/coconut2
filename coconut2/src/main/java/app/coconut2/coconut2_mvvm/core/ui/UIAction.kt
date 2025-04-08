package app.coconut2.coconut2_mvvm.core.ui

sealed class UIAction {
    object LoadData : UIAction()
    data class SubmitData<T>(val data: T) : UIAction()
}