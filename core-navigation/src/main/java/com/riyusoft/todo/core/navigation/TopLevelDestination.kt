package com.riyusoft.todo.core.navigation

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
) : TodoNavigationDestination
