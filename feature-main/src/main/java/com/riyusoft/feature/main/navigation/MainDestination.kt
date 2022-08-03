package com.riyusoft.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.riyusoft.feature.main.MainRoute
import com.riyusoft.todo.core.navigation.TodoNavigationDestination

object MainDestination : TodoNavigationDestination {
    override val route = "main_route"
    override val destination = "main_destination"
}

fun NavGraphBuilder.mainGraph() {
    composable(route = MainDestination.route) {
        MainRoute()
    }
}
