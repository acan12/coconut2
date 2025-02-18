package app.coconut2.sample.data.local

import androidx.room.Database
import app.coconut2.coconut2_mvvm.core.datasource.local.AppDatabase
import app.coconut2.sample.data.dao.UserDao
import app.coconut2.sample.data.model.entity.UserEntity

@Database(version = 1, exportSchema = false, entities = [UserEntity::class])
abstract class SampleDatabase : AppDatabase() {
    abstract fun userDao(): UserDao
}