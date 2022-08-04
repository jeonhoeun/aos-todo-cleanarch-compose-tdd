package com.riyusoft.todo.core.data.repository.todo

import com.riyusoft.todo.core.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalTodoRepository @Inject constructor() : TodoRepository {
    override fun getTodosStream(): Flow<List<Todo>> {
//        return flow{
//            emit(emptyList<Todo>())
//        }
        return flow {
            emit(
                listOf(
                    Todo(
                        "title1", "data1"
                    ),
                    Todo(
                        "title2", "data2"
                    ),
                    Todo(
                        "title3", "data3"
                    )
                )
            )
        }
        //  return listOf<List<Todo>>().asFlow()
//        return listOf(
//            listOf<Todo>(
//                Todo(
//                    "title1", "data1"
//                ),
//                Todo(
//                    "title2", "data2"
//                ),
//                Todo(
//                    "title3", "data3"
//                )
//            )
//        ).asFlow()
    }
}
