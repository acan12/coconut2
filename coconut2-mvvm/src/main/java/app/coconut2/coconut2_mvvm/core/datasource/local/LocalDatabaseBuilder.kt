//package app.coconut2.coconut2_mvvm.core.datasource.local
//
//import android.content.Context
//import androidx.room.Room
//
//class LocalDatabaseBuilder<T : AppDatabase>(
//    val context: Context,
//    val clazz: Class<T>,
//    val databaseName: String
//) {
//    fun build(): T =
//        Room.databaseBuilder(
//            context,
//            clazz,
//            databaseName
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//}