package org.ka7suk0sail.compapp.Themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    val lightColors = lightColorScheme(
        primary = Color(0xffe6a06d),
        primaryContainer = Color(0xffe6a06d),
        secondary = Color(0xFFe09f69),
        secondaryContainer = Color(0xffe09f69),
        background = BackgroundLightYellow/*Color(0xFFeddbab)*/,
        surface = BackgroundLightYellow/*Color(0xFFf5f4d6)*/,
        error = Color(0xFFE63946),
        onPrimary = Color(0xFFFFFFFF),
        onSecondary = Color(0xFFFFFFFF),
        onBackground = Color(0xFF1e1f22),
        onSurface = Color(0xFF1e1f22),
        onError = Color(0xFFFFFFFF)
    )

    val darkColors = darkColorScheme(
        primary = Color(0xffBB86FC),
        primaryContainer = Color(0xFF3700B3),
        secondary = Color(0xff03DAC6),
        secondaryContainer = Color(0xFF03DAC6),
        background = Color(0xFF121212),
        surface = Color(0xFF121212),
        error = Color(0xFFCF6679),
        onPrimary = Color(0xFF000000),
        onSecondary = Color(0xFF000000),
        onBackground = Color(0xFFFFFFFF),
        onSurface = Color(0xFFFFFFFF),
        onError = Color(0xFF000000)
    )
    val colorScheme = if(isSystemInDarkTheme()) darkColors else lightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Teal700 = Color(0xFF018786)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val PrimaryYellow = Color(0xFFFAD02C)
val SecondaryLightYellow = Color(0xFFFFE57D)
val SecondaryDarkYellow = Color(0xFFE2B505)
val ThirdLightYellow = Color(0xFFFFDC54)
val ThirdDarkYellow = Color(0xFFB08C00)
val PrimaryBrown = Color(0xFFC08B5C)
val PrimaryPurpleBrown = Color(0xFF795458)
val PrimaryNavy = Color(0xFF453F78)
val PrimaryBlack = Color(0xFF15133C)
val PrimaryGray = Color(0xFF73777B)
val PrimaryWhite = Color(0xFFF1EEE9)
val BackgroundLightYellow = Color(0xFFFFFBDA)
val PrimaryOrange = Color(0xFFFFAF45)