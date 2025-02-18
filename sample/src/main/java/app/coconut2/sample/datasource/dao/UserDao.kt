package app.coconut2.sample.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.coconut2.sample.datasource.model.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(data: UserEntity)

    @Query("SELECT * FROM user_tbl")
    fun read(): List<UserEntity>
}