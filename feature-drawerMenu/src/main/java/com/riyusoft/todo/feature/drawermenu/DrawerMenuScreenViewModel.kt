package com.riyusoft.todo.feature.drawermenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.model.TodoGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerMenuScreenViewModel @Inject constructor(
    todoRepository: TodoRepository
) : ViewModel() {
    var screenUiState = MutableStateFlow<DrawerMenuScreenUiState>(DrawerMenuScreenUiState.Loading)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getTodoGroups().collect {
                println("testtest:drawer viewmodel collected")
                screenUiState.value = DrawerMenuScreenUiState.Success(it)
            }
        }
    }
}

sealed interface DrawerMenuScreenUiState {
    object Loading : DrawerMenuScreenUiState
    data class Success(
        val todoGroups: List<TodoGroup>
    ) : DrawerMenuScreenUiState
}
