package com.riyusoft.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.riyusoft.todo.ui.TodoApp
import com.riyusoft.todo.ui.theme.RiyuTodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RiyuTodoTheme {
                TodoApp()
            }
        }
    }
}
