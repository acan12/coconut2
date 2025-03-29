package app.coconut2.sample.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.coconut2.coconut2_mvvm.network.ApiState
import app.coconut2.sample.data.local.entity.UserEntity
import app.coconut2.sample.domain.mapper.HeadlineData
import app.coconut2.sample.domain.usecase.GetUserUseCase
import app.coconut2.sample.domain.repo.user.IUserRepository
import app.coconut2.sample.domain.usecase.GetTopHeadlineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    private val getTopHeadlineUseCase: GetTopHeadlineUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _topHeadline: MutableLiveData<List<HeadlineData?>> = MutableLiveData()
    val topHeadline: LiveData<List<HeadlineData?>> = _topHeadline

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

    fun getTopHeadline() {
        viewModelScope.launch {
            getTopHeadlineUseCase().collect { result ->
                when(result){
                    is ApiState.Success -> {
                        _topHeadline.postValue(result.data)
                    }
                    is ApiState.Error<*> -> {
                        Log.d("TAG", result.message.toString())
                    }
                    else -> {
                        Log.d("TAG", "Loading.......")
                    }
                }
            }
        }
    }


}