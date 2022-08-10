package com.riyusoft.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.riyusoft.feature.main.MainRoute
import com.riyusoft.todo.core.navigation.TodoNavigationDestination

object MainDestination : TodoNavigationDestination {
    const val mainGroupIdArg = "mainGroupId"
    override val route = "main_route/{$mainGroupIdArg}"
    override val destination = "main_destination"

    fun createMainRoute(todoGroupIdArg: Long): String {
        return "main_route/$todoGroupIdArg"
    }
}

fun NavGraphBuilder.mainGraph(
    navigateToNewTodo: () -> Unit,
    navigateToEditTodo: (Long) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    composable(
        route = MainDestination.route,
        arguments = listOf(
            navArgument(MainDestination.mainGroupIdArg) {
                type = NavType.LongType
            }
        )
    ) {
        MainRoute(
            navigateToNewTodo = navigateToNewTodo,
            navigateToEditTodo = navigateToEditTodo
        )
    }
    nestedGraphs()
}
