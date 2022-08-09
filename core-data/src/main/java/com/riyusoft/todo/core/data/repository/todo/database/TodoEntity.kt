package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.riyusoft.todo.core.model.Todo
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "todo",
    foreignKeys = [
        ForeignKey(
            entity = TodoGroupEntity::class,
            parentColumns = ["id"],
            childColumns = ["group_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class TodoEntity(
    @NotNull @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "data") var description: String,
    @ColumnInfo(name = "priority") var priority: Long = -1,
    @ColumnInfo(name = "group_id", defaultValue = "2") var groupID: Long = 2
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    companion object {
        fun fromModel(todo: Todo): TodoEntity {
            return TodoEntity(
                title = todo.title,
                description = todo.description,
                priority = todo.priority
            ).apply {
                id = todo.id
            }
        }
    }
}

fun TodoEntity.toModel(): Todo {
    return Todo(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        groupId = this.groupID
    )
}
