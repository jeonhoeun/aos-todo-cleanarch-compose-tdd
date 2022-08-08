package com.riyusoft.todo.feature.edittodo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.Todo
import com.riyusoft.todo.feature.edittodo.navigation.EditTodoDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTodoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val todoRepository: TodoRepository
) : ViewModel() {

    private val todoId: Int = checkNotNull(savedStateHandle[EditTodoDestination.editTodoIdArg])

    var editTodoScreenUiState = MutableStateFlow<EditTodoScreenUiState>(
        EditTodoScreenUiState(
            EditTodoUiState("", "")
        )
    )
        private set

    init {
        println("testtest: todoID:$todoId")
        if (todoId >= 0) {
            viewModelScope.launch {
                todoRepository.getTodo(todoId)?.run {
                    editTodoScreenUiState.value = EditTodoScreenUiState(
                        EditTodoUiState(title, data)
                    )
                }
            }
        }
    }

    suspend fun onClickSave(title: String, data: String): Boolean {
        println("testtest:onClickSave start before runblocking")
        if (todoId == -1) {
            return todoRepository.addTodo(
                Todo(
                    id = todoId,
                    title = title,
                    data = data
                )
            ) >= 0
        } else {
            return todoRepository.editTodo(
                Todo(
                    id = todoId,
                    title = title,
                    data = data
                )
            ) >= 0
        }
    }
}

data class EditTodoUiState(
    val title: String,
    val data: String
)

data class EditTodoScreenUiState(
    val editTodoUiState: EditTodoUiState
)
