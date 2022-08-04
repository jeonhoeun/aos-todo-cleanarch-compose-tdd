package com.riyusoft.todo.core.data.repository.todo

import com.riyusoft.todo.core.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodosStream(): Flow<List<Todo>>
}
