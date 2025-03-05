package app.coconut2.sample.core.di.module

import android.content.Context
import app.coconut2.coconut2_mvvm.core.datasource.local.LocalDatabaseBuilder
import app.coconut2.coconut2_mvvm.network.ApiManager
import app.coconut2.sample.BuildConfig
import app.coconut2.sample.data.local.SampleDatabase
import app.coconut2.sample.data.remote.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = LocalDatabaseBuilder(
        context,
        SampleDatabase::class.java,
        BuildConfig.DB_NAME,
    ).build()

    @Provides
    @Singleton
    fun provideApi(): Api = Api(ApiManager())
}