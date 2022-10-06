package com.hieubui.jetflix.home.ui.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
internal fun LoadingIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = colors.primary,
        strokeWidth = 4.dp
    )
}

@Preview
@Composable
private fun LoadingIndicatorPreview() {
    JetflixTheme {
        LoadingIndicator()
    }
}
