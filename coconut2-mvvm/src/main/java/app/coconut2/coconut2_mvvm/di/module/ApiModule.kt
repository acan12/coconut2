package app.coconut2.coconut2_mvvm.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    fun provideRetrofit(){}
}