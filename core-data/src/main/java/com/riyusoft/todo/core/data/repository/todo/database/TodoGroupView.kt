package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT todo_group.id as id," +
        " todo_group.name as name, COUNT(todo.id) as todoCount" +
        " FROM todo_group JOIN todo" +
        " where todo.group_id=todo_group.id" +
        " group by todo_group.id"
)
data class TodoGroupView(
    val id: Long,
    val name: String,
    val todoCount: Long
)
