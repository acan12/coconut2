package app.coconut2.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.coconut2.sample.data.model.entity.UserEntity
import app.coconut2.sample.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){
    val allUsers: Flow<List<UserEntity>> = userRepository.allUsers

    fun insert(user: UserEntity) =
        viewModelScope.launch {
            userRepository.insert(user)
        }
}