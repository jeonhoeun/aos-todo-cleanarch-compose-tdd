package com.riyusoft.todo.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel()
) {
    SplashScreen(
        viewModel::onTimer
    )
}

@Composable
fun SplashScreen(
    onTimer: () -> Unit
) {

    LaunchedEffect(true) {
        println("before delay")
        Thread.sleep(3000L)
        println("after delay")
        onTimer()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xf5, 0xf5, 0xf5)
            )
    ) {
        Text(text = stringResource(id = R.string.riyusoft))
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        onTimer = {}
    )
}
