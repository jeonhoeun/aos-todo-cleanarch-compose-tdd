package com.riyusoft.todo.core.data.repository.todo.database

import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDatabaseTodoRepository @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun getTodoAllStream(): Flow<List<Todo>> {
        return flow {
            dao.getTodoAll().map {
                val returnValue = ArrayList<Todo>()
                it.forEach {
                    returnValue.add(it.toModel())
                }
                emit(returnValue)
            }.collect()
        }
    }

    override suspend fun getTodosByGroupId(groupId: Long): Flow<List<Todo>> {
        return flow {
            dao.getTodosByGroupId(groupId).map {
                val returnValue = ArrayList<Todo>()
                it.forEach {
                    returnValue.add(it.toModel())
                }
                emit(returnValue)
            }.collect()
        }
    }

    override suspend fun getTodoById(id: Long): Todo {
        return dao.getTodoById(id).toModel()
    }

    override suspend fun insertTodo(todo: Todo): Long {
        println("testtest:insert todo")
        return dao.insertTodo(
            TodoEntity.fromModel(todo)
        ).also {
            println("insert id : $it")
        }
    }

    override suspend fun updateTodo(todo: Todo): Int {
        println("testtest:updateTodo")
        return dao.updateTodo(
            TodoEntity.fromModel(todo)
        ).also {
            println("update id : $it")
        }
    }

    override suspend fun moveTodoToTrash(id: Long) {
        dao.moveTodoToTrash(id)
    }
}
