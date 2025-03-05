package app.coconut2.sample.core.di

import app.coconut2.sample.data.local.SampleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideUserDao(db: SampleDatabase) = db.userDao()

}