package app.coconut2.sample.di.module

import app.coconut2.coconut2_mvvm.network.ApiManager
import app.coconut2.sample.datasource.remote.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(): Api = Api(ApiManager())

}