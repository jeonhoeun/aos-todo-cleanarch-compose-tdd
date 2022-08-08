package com.riyusoft.todo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.tracing.trace
import com.riyusoft.todo.core.navigation.TodoNavigationDestination
import com.riyusoft.todo.core.navigation.TopLevelDestination

@Composable
fun rememberTodoAppState(
    navController: NavHostController = rememberNavController()
): TodoAppState {
    return remember(navController) {
        TodoAppState(navController)
    }
}

@Stable
class TodoAppState(
    val navController: NavHostController
) {

    fun navigate(destination: TodoNavigationDestination, route: String? = null) {
        println("testtest: navigate:$destination / route:$route")
        trace("Navigation: $destination") {
            when (destination is TopLevelDestination) {
                true -> {
                    navController.navigate(route ?: destination.route) {
                        // 탑레벨 목적지라면 중복 스택을 방지하기 위해서 시작점까지 돌아간다.
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true // 여러개 생성 방지
                        restoreState = true // 다시 네비게이션 될때 이전 상태를 사용
                    }
                }
                false -> {
                    navController.navigate(route ?: destination.route)
                }
            }
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}
