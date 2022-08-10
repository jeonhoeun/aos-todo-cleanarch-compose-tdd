package com.riyusoft.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.riyusoft.feature.main.navigation.MainDestination
import com.riyusoft.feature.main.navigation.mainGraph
import com.riyusoft.todo.core.model.DefaultTodoGroupId
import com.riyusoft.todo.core.navigation.TodoNavigationDestination
import com.riyusoft.todo.feature.edittodo.navigation.EditTodoDestination
import com.riyusoft.todo.feature.edittodo.navigation.editTodoGraph
import com.riyusoft.todo.feature.splash.navigation.SplashDestination
import com.riyusoft.todo.feature.splash.navigation.splashGraph

@Composable
fun TodoNavHost(
    navController: NavHostController,
    onNavigateToDestination: (TodoNavigationDestination, String?) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = SplashDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        splashGraph(
            navigateToMain = {
                onNavigateToDestination(
                    MainDestination, MainDestination.createMainRoute(DefaultTodoGroupId)
                )
            }
        )
        mainGraph(
            navigateToNewTodo = {
                onNavigateToDestination(
                    EditTodoDestination, EditTodoDestination.createEditTodoRoute(-1)
                )
            },
            navigateToEditTodo = {
                onNavigateToDestination(
                    EditTodoDestination, EditTodoDestination.createEditTodoRoute(it)
                )
            },
            nestedGraphs = {
                editTodoGraph(
                    onBackClick = onBackClick
                )
            }
        )
    }
}
