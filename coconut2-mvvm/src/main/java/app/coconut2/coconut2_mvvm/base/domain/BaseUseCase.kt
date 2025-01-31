package app.coconut2.coconut2_mvvm.base.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class BaseUseCase<T> {
    private var _trigger = MutableStateFlow(true)

    @ExperimentalCoroutinesApi
    val resultFlow: Flow<T> = _trigger.flatMapLatest {
        performAction()
    }

    protected abstract suspend fun performAction(): Flow<T>

    suspend fun launch() {
        _trigger.emit(!_trigger.value)
    }
}