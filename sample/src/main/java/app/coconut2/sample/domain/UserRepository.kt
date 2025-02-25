package app.coconut2.sample.domain

import app.coconut2.coconut2_mvvm.base.BaseRepository
import app.coconut2.sample.data.local.dao.UserDao
import app.coconut2.sample.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
): BaseRepository() {
    fun allUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    suspend fun insert(user: UserEntity) = userDao.insert(user)

    suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)

}