package com.riyusoft.todo.core.data.repository.todo

import android.graphics.Insets.add
import com.riyusoft.todo.core.model.Todo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMemoryTodoRepository @Inject constructor() : TodoRepository {

    private var latestIndex = 0
    private var mutableTodoEntityList = MutableStateFlow<List<Todo>>(emptyList())
    private val todoEntityList = arrayListOf<TodoEntity>()
    private val testDelay = 100L

    override suspend fun getTodosStream(): Flow<List<Todo>> {
        println("testtest:call getTodosStream")
        return mutableTodoEntityList
    }

    override suspend fun addTodo(todo: Todo): Int {
        println("testtest:addtodo start and delay")
        delay(testDelay)
        println("testtest::addtodo end delay")
        todoEntityList.add(
            TodoEntity(id = latestIndex, title = todo.title, data = todo.data)
        )

        mutableTodoEntityList.emit(
            arrayListOf<Todo>().apply {
                todoEntityList.forEach {
                    this.add(Todo(it.id, it.title, it.data))
                }
            }
        )

        println("testtest::addtodo after save")

        return latestIndex.also {
            latestIndex++
        }
    }

    override suspend fun editTodo(todo: Todo): Int {
        println("testtest:edittodo start and delay")
        delay(testDelay)
        println("testtest::edittodo end delay")

        var targetIdx = -1

        todoEntityList.forEachIndexed { idx, data ->
            if (data.id == todo.id) {
                targetIdx = idx
                return@forEachIndexed
            }
        }

        todoEntityList.removeAt(targetIdx)
        todoEntityList.add(
            targetIdx,
            todo.let {
                TodoEntity(
                    id = it.id,
                    title = it.title,
                    data = it.data
                )
            }
        )

        mutableTodoEntityList.emit(
            arrayListOf<Todo>().apply {
                todoEntityList.forEach {
                    this.add(Todo(it.id, it.title, it.data))
                }
            }
        )

        println("testtest::addtodo after save")

        return todo.id
    }

    override suspend fun getTodo(id: Int): Todo? {
        return todoEntityList.filter {
            it.id == id
        }.map {
            Todo(
                it.id,
                it.title,
                it.data
            )
        }.first()
    }
}
