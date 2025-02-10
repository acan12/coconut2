package app.coconut2.coconut2_mvvm.di.module

import android.content.Context
import androidx.room.Room
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): UsersDatabase {
        return Room.databaseBuilder(
            appContext,
            UsersDatabase::class.java,
            "Users"
        ).fallbackToDestructiveMigration().build()
    }
}