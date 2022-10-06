package com.hieubui.jetflix.home.ui.component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hieubui.jetflix.home.R
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = stringResource(id = R.string.filter),
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
private fun FilterButtonPreview() {
    JetflixTheme {
        FilterButton(onClick = { })
    }
}
