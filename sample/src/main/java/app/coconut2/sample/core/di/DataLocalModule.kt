package app.coconut2.sample.core.di

import android.content.Context
import android.content.SharedPreferences
import app.coconut2.coconut2_mvvm.core.datasource.local.cache.CacheDataPreference
import app.coconut2.sample.BuildConfig
import app.coconut2.sample.data.local.dao.UserDao
import app.coconut2.sample.data.local.data.TopHeadlineLocalData
import app.coconut2.sample.data.local.repo.UserLocalRepository
import app.coconut2.sample.data.remote.response.TopHeadlineResponse
import app.coconut2.sample.domain.repo.user.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataLocalModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)


    @Provides
    fun provideTopHeadlineData(sharedPreferences: SharedPreferences): TopHeadlineLocalData = TopHeadlineLocalData(sharedPreferences)
}