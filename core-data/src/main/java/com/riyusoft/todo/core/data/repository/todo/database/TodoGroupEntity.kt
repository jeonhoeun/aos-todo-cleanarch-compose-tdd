package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "todo_group")
data class TodoGroupEntity(
    @NotNull @ColumnInfo(name = "name") var name: String
) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}
