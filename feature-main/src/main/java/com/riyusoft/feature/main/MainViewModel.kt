package com.riyusoft.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    todoRepository: TodoRepository
) : ViewModel() {

    val uiState: StateFlow<MainScreenUiState> = todoRepository.getTodosStream().map {
        val todoUiState = if (it.isEmpty()) {
            TodoUiState.Empty
        } else {
            TodoUiState.Success(
                todos = it
            )
        }
        MainScreenUiState(
            todoState = todoUiState
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainScreenUiState(todoState = TodoUiState.Loading)
    )
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
