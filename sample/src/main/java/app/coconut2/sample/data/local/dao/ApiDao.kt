package app.coconut2.sample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.coconut2.sample.data.local.entity.ApiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: ApiEntity)

    @Query("SELECT * FROM api WHERE name = :name")
    fun getResponseByName(name: String): Flow<ApiEntity>

    @Query("DELETE FROM api WHERE name = :name")
    suspend fun deleteByName(name: String)
}