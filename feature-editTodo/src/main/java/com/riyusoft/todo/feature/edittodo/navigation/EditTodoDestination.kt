package com.riyusoft.todo.feature.edittodo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.riyusoft.todo.core.navigation.TodoNavigationDestination
import com.riyusoft.todo.feature.edittodo.EditTodoRoute

object EditTodoDestination : TodoNavigationDestination {
    const val editTodoIdArg = "editTodoId"
    override val route = "edit_todo_route/{$editTodoIdArg}"
    override val destination = "edit_todo_destination"

    fun createEditTodoRoute(editTodoIdArg: Int): String {
        return "edit_todo_route/$editTodoIdArg"
    }
}

fun NavGraphBuilder.editTodoGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = EditTodoDestination.route,
        arguments = listOf(
            navArgument(EditTodoDestination.editTodoIdArg) {
                type = NavType.IntType
            }
        )
    ) {
        EditTodoRoute(
            onBackClick = onBackClick
        )
    }
}
