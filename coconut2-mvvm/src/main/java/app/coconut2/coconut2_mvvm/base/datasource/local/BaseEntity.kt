package app.coconut2.coconut2_mvvm.base.datasource.local

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.UUID

open class BaseEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: UUID = UUID.randomUUID()
}