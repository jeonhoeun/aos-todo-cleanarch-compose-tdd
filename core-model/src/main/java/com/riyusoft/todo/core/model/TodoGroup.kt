package com.riyusoft.todo.core.model

const val TrashTodoGroupId = 1L
const val DefaultTodoGroupId = 2L
data class TodoGroup(
    val id: Long,
    val name: String,
    val todoCount: Long
)
