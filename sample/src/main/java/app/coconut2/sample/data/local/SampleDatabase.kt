package app.coconut2.sample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.coconut2.sample.data.dao.UserDao
import app.coconut2.sample.data.model.entity.UserEntity

@Database(version = 1, exportSchema = false, entities = [UserEntity::class])
abstract class SampleDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}