package app.coconut2.coconut2_mvvm.di.module

import android.content.Context
import androidx.room.RoomDatabase
import app.coconut2.coconut2_mvvm.core.datasource.local.LocalDatabaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(clazz: Class<RoomDatabase>, @ApplicationContext appContext: Context) =
        LocalDatabaseBuilder(clazz, appContext).build()

}