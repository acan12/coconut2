package app.coconut2.sample.domain.repo.user

import app.coconut2.sample.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun allUsers(): Flow<List<UserEntity>>

    suspend fun insert(user: UserEntity)

    suspend fun deleteUser(user: UserEntity)
}