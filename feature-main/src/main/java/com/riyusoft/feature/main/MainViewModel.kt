package com.riyusoft.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val todoRepository: TodoRepository
) : ViewModel() {

    var uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.ShowData(todoState = TodoUiState.Loading))
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getTodosByGroupId(2).map {
                val todoUiState = if (it.isEmpty()) {
                    TodoUiState.Empty
                } else {
                    TodoUiState.Success(
                        todos = it
                    )
                }
                uiState.value = MainScreenUiState.ShowData(todoUiState)
            }.collect()
        }
    }

    fun onClickDelete(id: Long?, title: String) {
        id?.run {
            uiState.value = MainScreenUiState.ShowDeleteDialog(
                id = id,
                title = title,
                onClickOk = {
                    viewModelScope.launch(Dispatchers.IO) {
                        todoRepository.moveTodoToTrash(id)
                    }
                },
                onClickCancel = {
                    viewModelScope.launch(Dispatchers.IO) {
                        todoRepository.getTodosByGroupId(2).map {
                            val todoUiState = if (it.isEmpty()) {
                                TodoUiState.Empty
                            } else {
                                TodoUiState.Success(
                                    todos = it
                                )
                            }
                            uiState.value = MainScreenUiState.ShowData(todoUiState)
                        }.collect()
                    }
                }
            )
        }
    }
}

sealed interface MainScreenUiState {
    data class ShowDeleteDialog(
        val id: Long,
        val title: String,
        val onClickOk: () -> Unit,
        val onClickCancel: () -> Unit
    ) : MainScreenUiState

    data class ShowData(
        val todoState: TodoUiState
    ) : MainScreenUiState
}

sealed interface TodoUiState {
    object Loading : TodoUiState
    data class Success(
        val todos: List<Todo>
    ) : TodoUiState

    object Empty : TodoUiState
}
