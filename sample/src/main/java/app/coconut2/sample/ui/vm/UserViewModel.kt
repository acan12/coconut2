package app.coconut2.sample.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.coconut2.sample.data.local.entity.UserEntity
import app.coconut2.sample.domain.usecase.GetUserUseCase
import app.coconut2.sample.domain.repo.user.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    getUserUseCase: GetUserUseCase
) : ViewModel() {

    val userLanguage: String? = getUserUseCase(listOf("en", "id"))

    val allUsers: LiveData<List<UserEntity>> = userRepository.allUsers().asLiveData()

    fun deleteLastUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }

    fun insert(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }


}