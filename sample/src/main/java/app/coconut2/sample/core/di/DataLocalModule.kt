package app.coconut2.sample.core.di

import android.content.Context
import android.content.SharedPreferences
import app.coconut2.sample.BuildConfig
import app.coconut2.sample.data.local.data.TopHeadlineLocal
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
    fun provideTopHeadlineData(sharedPreferences: SharedPreferences): TopHeadlineLocal = TopHeadlineLocal(sharedPreferences)
}