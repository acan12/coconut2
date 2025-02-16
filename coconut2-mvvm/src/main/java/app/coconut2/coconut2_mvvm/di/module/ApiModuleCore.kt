package app.coconut2.coconut2_mvvm.di.module

import app.coconut2.coconut2_mvvm.network.ApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModuleCore {

//    @Provides
//    @Singleton
//    fun provideRemoteData(): SourceRemoteDataSource = SourceRemoteDataSource(provideApi())

//    @Provides
//    @Singleton
//    fun provideApi(): Api = Api(provideApiService())

//    @Provides
//    @Singleton
//    fun provideRetrofit(): IApiManager = ApiManagerManager()

}