package com.riyusoft.todo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.riyusoft.todo.R

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun RiyuTodoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val notosanskr = FontFamily(
        Font(R.font.notosans_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.notosans_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.notosans_regular, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.notosans_light, FontWeight.Light, FontStyle.Normal),
        Font(R.font.notosans_thin, FontWeight.Thin, FontStyle.Normal),
    )

    MaterialTheme(
        colors = colors,
        typography = Typography(
            defaultFontFamily = notosanskr,
//            body1 = TextStyle(
//                fontFamily = notosanskr,
//                platformStyle = PlatformTextStyle(
//                    includeFontPadding = false
//                )
//            )
        ),
        shapes = Shapes,
        content = content
    )
}
