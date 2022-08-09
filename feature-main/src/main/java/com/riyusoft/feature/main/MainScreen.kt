package com.riyusoft.feature.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riyusoft.todo.core.model.Todo

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainRoute(
    navigateToNewTodo: () -> Unit,
    navigateToEditTodo: (Long) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val uiState: MainScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreen(
        navigateToNewTodo = navigateToNewTodo,
        navigateToEditTodo = navigateToEditTodo,
        onClickDelete = viewModel::onClickDelete,
        uiState = uiState
    )
}

@Composable
fun ShowDeleteDialog(
    title: String,
    onClickOk: () -> Unit,
    onClickCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(232.dp)
                .height(190.dp),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(3.dp, Color(0x35, 0x90, 0x63)),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    color = Color.Black,
                )
                Text(
                    text = stringResource(id = R.string.main_delete_dialog_desc),
                    fontSize = 24.sp,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.clickable {
                            onClickOk()
                        }.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = android.R.string.ok)
                        )
                    }
                    Divider(
                        modifier = Modifier.height(20.dp).width(1.dp),
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier.clickable {
                            onClickCancel()
                        }.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = stringResource(id = android.R.string.cancel))
                    }
                }
            }
        }
    }
}

@Composable
fun ShowLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(232.dp)
                .height(190.dp),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(3.dp, Color(0x35, 0x90, 0x63))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.main_loading_desc),
                    fontSize = 24.sp,
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
fun ShowEmpty(
    navigateToNewTodo: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .testTag("empty_desc_text")
                .fillMaxWidth(),
            text = stringResource(id = R.string.main_empty_desc),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,

            color = Color(0x8d, 0x8d, 0x8d)
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { navigateToNewTodo() },
            modifier = Modifier
                .testTag("new_button")
                .clip(CircleShape)
                .background(Color(0xcc, 0xf6, 0xf1))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add job",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(68.dp))
    }
}

@Composable
fun ShowTodoList(
    todos: List<Todo>,
    navigateToEditTodo: (Long) -> Unit,
    navigateToNewTodo: () -> Unit,
    onClickDelete: (Long?, String) -> Unit
) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(58.dp))
        }
        todos.forEach { todo ->
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 32.dp)
                        .clickable {
                            navigateToEditTodo(todo.id ?: -1)
                        }
                        .drawBehind {
                            val strokeWidth = 1.dp.value * density
                            val y = size.height - strokeWidth / 2

                            drawLine(
                                Color.LightGray,
                                Offset(0f, y),
                                Offset(size.width, y),
                                strokeWidth
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = todo.title,
                        color = Color.Black,
                        fontSize = 24.sp
                    )

                    IconButton(
                        onClick = { onClickDelete(todo.id, todo.title) },
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(30.dp)
                            .height(30.dp)
                            .background(Color(0xEA, 0x16, 0x16))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete job",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { navigateToNewTodo() },
            modifier = Modifier
                .testTag("new_button")
                .clip(CircleShape)
                .background(Color(0xcc, 0xf6, 0xf1))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add job",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(68.dp))
    }
}

@Composable
fun MainScreen(
    navigateToNewTodo: () -> Unit,
    navigateToEditTodo: (Long) -> Unit,
    onClickDelete: (Long?, String) -> Unit,
    uiState: MainScreenUiState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xe5, 0xf6, 0xee))
    ) {

        when {
            uiState is MainScreenUiState.ShowDeleteDialog -> {
                ShowDeleteDialog(
                    title = uiState.title,
                    onClickOk = uiState.onClickOk,
                    onClickCancel = uiState.onClickCancel
                )
            }
            uiState is MainScreenUiState.ShowData && uiState.todoState is TodoUiState.Loading -> {
                ShowLoading()
            }
            uiState is MainScreenUiState.ShowData && uiState.todoState is TodoUiState.Empty -> {
                ShowEmpty(navigateToNewTodo)
            }
            uiState is MainScreenUiState.ShowData && uiState.todoState is TodoUiState.Success -> {
                ShowTodoList(
                    todos = uiState.todoState.todos,
                    navigateToEditTodo = navigateToEditTodo,
                    navigateToNewTodo = navigateToNewTodo,
                    onClickDelete = onClickDelete
                )
            }
        }
    }
}

// @Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
// @Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
// @Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
// @Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
// @Composable
// fun MainScreenLoadingPreview() {
//    MainScreen(
//        todoUiState = TodoUiState.Loading
//    )
// }
//
// @Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
// @Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
// @Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
// @Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
// @Preview
// @Composable
// fun MainScreenEmptyPreview() {
//    MainScreen(
//        navigateToNewTodo = {},
//        navigateToEditTodo = { _ -> },
//        todoUiState = TodoUiState.Empty
//    )
// }
//
// @Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
// @Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
// @Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
// @Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
// @Preview
// @Composable
// fun MainScreenTodosPreview() {
//    MainScreen(
//        navigateToNewTodo = {},
//        navigateToEditTodo = {},
//        onClickDelete = {_,_->},
//        uiState = TodoUiState.Success(
//            todos = listOf(
//                Todo(
//                    title = "title1",
//                    description = "data1",
//                    groupId = 1
//                ),
//                Todo(
//                    title = "title2",
//                    description = "data2",
//                    groupId = 1
//                ),
//                Todo(
//                    title = "title3",
//                    description = "data3",
//                    groupId = 1
//                )
//            )
//        )
//    )
// }
