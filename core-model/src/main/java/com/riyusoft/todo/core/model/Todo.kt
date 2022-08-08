package com.riyusoft.todo.core.model

data class Todo(
    val id: Long? = null,
    val title: String,
    val description: String,
    val priority: Long = -1
)
