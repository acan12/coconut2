package app.coconut2.sample.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.coconut2.sample.data.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: UserEntity)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Flow<List<UserEntity>>
}