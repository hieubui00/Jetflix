package com.hieubui.jetflix.resources.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun JetflixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val palette = if (darkTheme) DarkPalette else LightPalette

    MaterialTheme(
        colors = palette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}