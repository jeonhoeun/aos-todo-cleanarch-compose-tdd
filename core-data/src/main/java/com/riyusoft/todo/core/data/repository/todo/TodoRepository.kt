package com.riyusoft.todo.core.data.repository.todo

import com.riyusoft.todo.core.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodosStream(): Flow<List<Todo>>
    suspend fun addTodo(todo: Todo): Int
    suspend fun editTodo(todo: Todo): Int
    suspend fun getTodo(id: Int): Todo?
}
