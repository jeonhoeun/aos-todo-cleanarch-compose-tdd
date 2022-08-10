package com.riyusoft.todo.core.data.repository.todo.database

import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.Todo
import com.riyusoft.todo.core.model.TodoGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDatabaseTodoRepository @Inject constructor(
    private val dao: TodoDao,
    private val groupDao: TodoGroupDao
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

    override fun getTodoGroups(): Flow<List<TodoGroup>> {
        return flow<List<TodoGroup>> {
//            groupDao.getView().map {
//                println("test3 view updated")
//                emit(it.map {
//                    TodoGroup(
//                        id = it.id,
//                        name = it.name,
//                        todoCount = it.todoCount
//                    )
//                })
//            }.collect()

            dao.getTodoAll().combine(
                groupDao.getTodoGroupsAll()
            ) { todos, groups ->
                val returnValue = ArrayList<TodoGroup>()
                groups.forEach { group ->
                    var todoCount = 0L
                    todos.forEach { todo ->
                        if (group.id == todo.groupID) {
                            todoCount++
                        }
                    }
                    group.id?.run {
                        returnValue.add(
                            TodoGroup(
                                id = this,
                                name = group.name,
                                todoCount = todoCount
                            )
                        )
                    }
                }
                emit(returnValue)
            }.collect()
        }
    }
}
