package com.example.taskhero.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = Blue,
    background = BlackBackground,
    surface = DarkGrey,
    onPrimary = LightText,
    onBackground = LightText,
    onSurface = LightText,
)

@Composable
fun TaskHeroTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography,
        content = content
    )
}