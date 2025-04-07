package app.coconut2.sample.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.UUID

@Entity(tableName = "api")
data class ApiEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "value")
    val value: String,
    @ColumnInfo(name = "update_at")
    val updateAt: Calendar = Calendar.getInstance()
)