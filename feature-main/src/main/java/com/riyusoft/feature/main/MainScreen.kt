package com.riyusoft.feature.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel()
) {

    val uiState: MainScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreen(
        todoUiState = uiState.todoState
    )
}

@Composable
fun MainScreen(
    todoUiState: TodoUiState
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xe5, 0xf6, 0xee))
    ) {
        println("testtest:todoUiState : $todoUiState")
        when (todoUiState) {
            TodoUiState.Loading -> {
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
            is TodoUiState.Success -> {

                LazyColumn {
                    item {
                        Spacer(modifier = Modifier.height(58.dp))
                    }
                    todoUiState.todos.forEach { todo ->
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .padding(horizontal = 32.dp)
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
                                    text = todo.title,
                                    color = Color.Black,
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { /*TODO*/ },
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
            TodoUiState.Empty -> {
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
                        onClick = { /*TODO*/ },
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
@Preview
@Composable
fun MainScreenEmptyPreview() {
    MainScreen(
        todoUiState = TodoUiState.Empty
    )
}
//
// @Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
// @Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
// @Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
// @Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
// @Composable
// fun MainScreenTodosPreview() {
//    MainScreen(
//        todoUiState = TodoUiState.Success(
//            todos = listOf(
//                Todo(
//                    title = "title1",
//                    data="data1"
//                ),
//                Todo(
//                    title = "title2",
//                    data="data2"
//                ),
//                Todo(
//                    title = "title3",
//                    data="data3"
//                )
//            )
//        )
//    )
// }
