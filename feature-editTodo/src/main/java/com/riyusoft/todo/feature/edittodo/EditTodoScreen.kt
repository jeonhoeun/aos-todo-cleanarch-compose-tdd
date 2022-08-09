package com.riyusoft.todo.feature.edittodo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EditTodoRoute(
    onBackClick: () -> Unit,
    viewModel: EditTodoViewModel = hiltViewModel()
) {

    val screenUiState: EditTodoScreenUiState by viewModel.editTodoScreenUiState.collectAsStateWithLifecycle()
    EditTodoScreen(
        uiState = screenUiState,
        onClickSave = viewModel::onClickSave,
        onBackClick = onBackClick
    )
}

@Composable
fun EditTodoScreen(
    uiState: EditTodoScreenUiState,
    onClickSave: (String, String, (Boolean) -> Unit) -> Unit,
    onBackClick: () -> Unit
) {

    if (uiState is EditTodoScreenUiState.Success) {

        val title = remember {
            mutableStateOf(uiState.editTodoUiState.title)
        }

        val desc = remember {
            mutableStateOf(uiState.editTodoUiState.description)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0x5c, 0xff, 0xff)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 32.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xff, 0xff, 0xff, 64),
                    focusedIndicatorColor = Color(0, 0, 0, 0),
                    unfocusedIndicatorColor = Color(0, 0, 0, 0),
                    disabledIndicatorColor = Color(0, 0, 0, 0),
                    errorIndicatorColor = Color(0, 0, 0, 0),
                ),
                shape = RoundedCornerShape(8.dp),
                value = title.value,
                onValueChange = { title.value = it },
                placeholder = {
                    Text(
                        text = stringResource(R.string.edittodo_title_hint),
                        color = Color(0x81, 0x81, 0x81, 0xFF),
                        fontSize = 20.sp
                    )
                },
            )
            Spacer(modifier = Modifier.height(44.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 32.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xff, 0xff, 0xff, 64),
                    focusedIndicatorColor = Color(0, 0, 0, 0),
                    unfocusedIndicatorColor = Color(0, 0, 0, 0),
                    disabledIndicatorColor = Color(0, 0, 0, 0),
                    errorIndicatorColor = Color(0, 0, 0, 0),
                ),
                shape = RoundedCornerShape(8.dp),
                value = desc.value,
                onValueChange = { desc.value = it },
                placeholder = {
                    Text(
                        text = stringResource(R.string.edittodo_desc_hint),
                        color = Color(0x81, 0x81, 0x81, 0xFF),
                        fontSize = 20.sp
                    )
                },
            )
            Spacer(modifier = Modifier.height(122.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    onClickSave(title.value, desc.value) {
                        if (it) {
                            onBackClick()
                        }
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xcc, 0xf6, 0xf1))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit job",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(21.dp))
        }
    }
}
