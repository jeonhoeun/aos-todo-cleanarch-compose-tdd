package com.riyusoft.todo.feature.splash

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class SplashScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `SplashScreen_디자인가이드`() {
        composeTestRule.setContent {
            SplashScreen(
                onTimer = {}
            )
        }

        with(composeTestRule) {
            onNodeWithText("R I Y U S O F T")
                .assertIsDisplayed()
        }
    }
}
