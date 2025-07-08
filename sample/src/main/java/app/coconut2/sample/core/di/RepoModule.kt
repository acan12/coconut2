package app.coconut2.sample.core.di

import app.coconut2.sample.data.local.dao.UserDao
import app.coconut2.sample.data.local.data.TopHeadlineLocal
import app.coconut2.sample.data.local.repo.UserLocalRepository
import app.coconut2.sample.data.remote.Api
import app.coconut2.sample.data.remote.repo.HeadlineRepository
import app.coconut2.sample.domain.repo.headline.IHeadlineRepository
import app.coconut2.sample.domain.repo.user.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideUserRepository(userDao: UserDao): IUserRepository = UserLocalRepository(userDao)

    @Provides
    fun provideHeadlineRepository(
        api: Api,
        topHeadlineLocalData: TopHeadlineLocal
    ): IHeadlineRepository =
        HeadlineRepository(api, topHeadlineLocalData)
}