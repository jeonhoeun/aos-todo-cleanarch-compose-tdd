package com.riyusoft.todo.core.data.repository.todo

import com.riyusoft.todo.core.model.Todo
import com.riyusoft.todo.core.model.TodoGroup
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodoAllStream(): Flow<List<Todo>>
    suspend fun getTodosByGroupId(groupId: Long): Flow<List<Todo>>
    suspend fun getTodoById(id: Long): Todo
    suspend fun insertTodo(todo: Todo): Long
    suspend fun updateTodo(todo: Todo): Int
    suspend fun moveTodoToTrash(id: Long)

    fun getTodoGroups(): Flow<List<TodoGroup>>
}
