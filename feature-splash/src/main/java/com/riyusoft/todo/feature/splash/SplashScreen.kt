package com.riyusoft.todo.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    navigateToMain: () -> Unit
) {
    SplashScreen(
        navigateToMain = navigateToMain
    )
}

@Composable
fun SplashScreen(
    navigateToMain: () -> Unit
) {

    LaunchedEffect(true) {
        delay(100L)
        navigateToMain()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xf5, 0xf5, 0xf5)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.riyusoft))
    }
}

// @Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
// @Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
// @Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
// @Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
// @Composable
// fun SplashScreenPreview() {
//    SplashScreen(
//        navigateToMain = {}
//    )
// }
