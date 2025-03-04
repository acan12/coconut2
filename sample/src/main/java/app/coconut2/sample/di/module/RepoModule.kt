package app.coconut2.sample.di.module

import app.coconut2.sample.data.local.dao.UserDao
import app.coconut2.sample.domain.user.IUserRepository
import app.coconut2.sample.data.local.repo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideUserRepository(userDao: UserDao): IUserRepository = UserRepository(userDao)
}