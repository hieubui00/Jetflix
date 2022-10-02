package com.hieubui.jetflix.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieubui.jetflix.home.R
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
internal fun ActionBar(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = isSystemInDarkTheme()
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 56.dp)
            .background(MaterialTheme.colors.surface)
            .then(modifier),
        elevation = 8.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(     // Settings button
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(size = 24.dp),
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(id = R.string.settings),
                    tint = MaterialTheme.colors.onSurface
                )
            }

            Image(      // Logo
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.logo)
            )

            IconToggleButton(   // Theme Mode button
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(size = 24.dp),
                checked = isDarkMode,
                onCheckedChange = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sunny),
                    contentDescription = stringResource(id = R.string.theme_mode),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
private fun ActionBarPreview() {
    JetflixTheme {
        ActionBar()
    }
}