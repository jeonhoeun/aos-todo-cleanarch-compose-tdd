package com.riyusoft.todo.feature.edittodo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.Todo
import com.riyusoft.todo.feature.edittodo.navigation.EditTodoDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTodoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val todoRepository: TodoRepository
) : ViewModel() {

    private val todoId: Long = checkNotNull(savedStateHandle[EditTodoDestination.editTodoIdArg])
    private lateinit var editingTodo: Todo

    var editTodoScreenUiState = MutableStateFlow<EditTodoScreenUiState>(EditTodoScreenUiState.Loading)
        private set

    init {
        println("testtest: todoID:$todoId")
        if (todoId >= 0) {

            viewModelScope.launch(Dispatchers.IO) {
                editingTodo = todoRepository.getTodoById(todoId).also {
                    println("testtest: title:${it.title}")
                    launch(Dispatchers.Main) {
                        editTodoScreenUiState.value = EditTodoScreenUiState.Success(
                            EditTodoUiState(
                                title = it.title,
                                description = it.description
                            )
                        )
                    }
                }
            }
        } else {
            editingTodo = Todo(
                title = "",
                description = "",
                groupId = 2
            )
            editTodoScreenUiState.value = EditTodoScreenUiState.Success(
                EditTodoUiState(
                    title = "",
                    description = ""
                )
            )
        }
    }

    fun onClickSave(title: String, description: String, callback: (Boolean) -> Unit) {
        println("testtest:onClickSave start before runblocking")

        viewModelScope.launch(Dispatchers.IO) {
            println("testtest: todo id : $todoId")
            if (todoId == -1L) {
                todoRepository.insertTodo(
                    Todo(
                        title = title,
                        description = description,
                        groupId = 2
                    )
                ).also {
                    launch(Dispatchers.Main) {
                        callback(it >= 0)
                    }
                }
            } else {
                todoRepository.updateTodo(
                    Todo(
                        id = todoId,
                        title = title,
                        description = description,
                        priority = editingTodo.priority,
                        groupId = editingTodo.groupId
                    )
                ).also {
                    launch(Dispatchers.Main) {
                        callback(it >= 0)
                    }
                }
            }
        }
    }
}

data class EditTodoUiState(
    val title: String,
    val description: String
)

sealed interface EditTodoScreenUiState {
    object Loading : EditTodoScreenUiState
    data class Success(
        val editTodoUiState: EditTodoUiState
    ) : EditTodoScreenUiState
}
