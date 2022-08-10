package com.riyusoft.todo.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.riyusoft.todo.R

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {

    val appBarBgColor = Color(0x09, 0xc3, 0xc3)

    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.app_name)
            )
        },
        backgroundColor = appBarBgColor,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
            }
        }
    )
}
