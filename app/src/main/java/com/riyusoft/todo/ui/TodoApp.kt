package com.riyusoft.todo.ui

import androidx.compose.runtime.Composable
import com.riyusoft.todo.navigation.TodoNavHost

@Composable
fun TodoApp(
    appState: TodoAppState = rememberTodoAppState()
) {
    TodoNavHost(
        navController = appState.navController,
        onNavigateToDestination = appState::navigate,
        onBackClick = appState::onBackClick
    )
}
