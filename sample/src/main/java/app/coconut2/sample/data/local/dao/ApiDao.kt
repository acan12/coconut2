package app.coconut2.sample.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
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
    fun getFromName(name: String): Flow<ApiEntity>

    @Delete
    suspend fun deleteApi(api: ApiEntity)
}