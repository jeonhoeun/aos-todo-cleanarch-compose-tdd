package com.riyusoft.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.riyusoft.feature.main.navigation.MainDestination
import com.riyusoft.feature.main.navigation.mainGraph
import com.riyusoft.todo.core.navigation.TodoNavigationDestination
import com.riyusoft.todo.feature.splash.navigation.SplashDestination
import com.riyusoft.todo.feature.splash.navigation.splashGraph

@Composable
fun TodoNavHost(
    navController: NavHostController,
    onNavigateToDestination: (TodoNavigationDestination, String?) -> Unit,
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
                onNavigateToDestination(MainDestination, null)
            }
        )
        mainGraph()
    }
}
