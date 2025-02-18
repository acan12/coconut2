package app.coconut2.sample.datasource.local

import androidx.room.Database
import app.coconut2.coconut2_mvvm.core.datasource.local.AppDatabase
import app.coconut2.sample.datasource.dao.UserDao
import app.coconut2.sample.datasource.model.entity.UserEntity

@Database(version = 1, entities = [UserEntity::class])
abstract class SampleDatabase : AppDatabase() {
    abstract fun userDao(): UserDao
}