package app.coconut2.sample.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import app.coconut2.coconut2_mvvm.base.datasource.local.BaseEntity

@Entity(tableName = "user_table")
data class UserEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "age")
    val age: String
) : BaseEntity()