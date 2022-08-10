package com.riyusoft.todo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.riyusoft.feature.main.navigation.MainDestination
import com.riyusoft.todo.feature.drawermenu.DrawerMenuBody
import com.riyusoft.todo.feature.drawermenu.DrawerMenuHeader
import com.riyusoft.todo.navigation.TodoNavHost
import kotlinx.coroutines.launch

@Composable
fun TodoApp(
    appState: TodoAppState = rememberTodoAppState()
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerMenuHeader()
            DrawerMenuBody(
                onClickTodoGroup = { todoGroupId ->
                    scope.launch {
                        appState.navigate(MainDestination, MainDestination.createMainRoute(todoGroupId))
                        scaffoldState.drawerState.close()
                    }
                }
            )
        }
    ) { padding ->
        TodoNavHost(
            modifier = Modifier.padding(padding),
            navController = appState.navController,
            onNavigateToDestination = appState::navigate,
            onBackClick = appState::onBackClick
        )
    }
}
