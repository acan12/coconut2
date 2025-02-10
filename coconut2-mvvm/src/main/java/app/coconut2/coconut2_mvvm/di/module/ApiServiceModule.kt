package app.coconut2.coconut2_mvvm.di.module

import app.coconut2.coconut2_mvvm.di.manager.ApiServiceManager
import app.coconut2.coconut2_mvvm.interfaces.IApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideRemoteData(): SourceRemoteDataSource = SourceRemoteDataSource(provideApi())

    @Provides
    @Singleton
    fun provideApi(): Api = Api(provideApiService())

    @Provides
    @Singleton
    fun provideApiService(): IApiService = ApiServiceManager()

}