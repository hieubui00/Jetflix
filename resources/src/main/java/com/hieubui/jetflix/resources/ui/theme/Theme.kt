package com.hieubui.jetflix.resources.ui.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun JetflixTheme(
    darkTheme: Boolean = isNightMode(),
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

@Composable
private fun isNightMode() = when (AppCompatDelegate.getDefaultNightMode()) {
    MODE_NIGHT_NO -> false

    MODE_NIGHT_YES -> true

    else -> isSystemInDarkTheme()
}
