package com.riyusoft.todo.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.riyusoft.todo.core.navigation.TodoNavigationDestination
import com.riyusoft.todo.feature.splash.SplashRoute

object SplashDestination : TodoNavigationDestination {
    override val route = "splash_route"
    override val destination = "splash_destination"
}

fun NavGraphBuilder.splashGraph(
    navigateToMain: () -> Unit
) {

    composable(route = SplashDestination.route) {
        SplashRoute(
            navigateToMain = navigateToMain
        )
    }
}
