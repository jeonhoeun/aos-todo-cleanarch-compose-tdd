package com.riyusoft.todo.feature.drawermenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DrawerMenuHeader() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 20.dp)
//            .background(Color.Red)
//    ) {
//        Text("Header", fontSize = 60.sp)
//    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DrawerMenuBody(
    onClickTodoGroup: (Long) -> Unit,
    viewModel: DrawerMenuScreenViewModel = hiltViewModel()
) {

    val uiState: DrawerMenuScreenUiState by viewModel.screenUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0XAC, 0XFC, 0XED)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        when {
            uiState is DrawerMenuScreenUiState.Loading -> {
                Text(text = "loading")
            }
            uiState is DrawerMenuScreenUiState.Success -> {
                var trashCount = 0L
                (uiState as DrawerMenuScreenUiState.Success).todoGroups.forEach {
                    if (it.id > 1) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onClickTodoGroup(it.id)
                                },
                            text = it.name,
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }

                    if (it.id == 1L) {
                        trashCount = it.todoCount
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                        .clickable {
                            onClickTodoGroup(1)
                        },
                    painter = painterResource(id = R.drawable.drawermenu_ic_trash),
                    contentDescription = "trash"
                )
                Text(text = "$trashCount")
                Spacer(modifier = Modifier.height(38.dp))
            }
        }
    }
}
