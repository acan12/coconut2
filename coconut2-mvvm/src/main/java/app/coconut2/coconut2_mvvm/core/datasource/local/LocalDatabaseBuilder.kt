package app.coconut2.coconut2_mvvm.core.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class LocalDatabaseBuilder<T: RoomDatabase>(val clazz: Class<T>, val context: Context) {
    fun build(): T =
        Room.databaseBuilder(
            context,
            clazz,
            "coconut-sample-db"
        ).build()
}