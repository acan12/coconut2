package app.coconut2.sample.data.local.repo

import app.coconut2.coconut2_mvvm.base.BaseRepository
import app.coconut2.sample.data.local.dao.UserDao
import app.coconut2.sample.data.local.entity.UserEntity
import app.coconut2.sample.domain.repo.user.IUserRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(
    private val userDao: UserDao
): IUserRepository, BaseRepository() {
    override fun allUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    override suspend fun insert(user: UserEntity) = userDao.insert(user)

    override suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)
}