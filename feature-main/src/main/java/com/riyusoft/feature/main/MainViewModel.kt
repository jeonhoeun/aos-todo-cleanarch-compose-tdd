package com.riyusoft.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    todoRepository: TodoRepository
) : ViewModel() {

    var uiState = MutableStateFlow(MainScreenUiState(todoState = TodoUiState.Loading))
        private set

    init {
        viewModelScope.launch {
            println("testtest:try get todo list")
            todoRepository.getTodosStream().map {
                println("map start")
                val todoUiState = if (it.isEmpty()) {
                    TodoUiState.Empty
                } else {
                    TodoUiState.Success(
                        todos = it
                    )
                }
                uiState.value = MainScreenUiState(todoUiState)
            }.collect()
        }
    }
}

sealed interface TodoUiState {
    object Loading : TodoUiState
    data class Success(
        val todos: List<Todo>
    ) : TodoUiState

    object Empty : TodoUiState
}

data class MainScreenUiState(
    val todoState: TodoUiState
)
