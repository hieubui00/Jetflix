package com.hieubui.jetflix.home.ui.component

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
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
            .background(colors.surface)
            .then(modifier),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .height(height = 56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton( // Settings button
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(size = 24.dp),
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(id = R.string.settings),
                    tint = colors.onSurface
                )
            }

            Image( // Logo
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.logo)
            )

            IconToggleButton( // Theme Mode button
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(size = 24.dp),
                checked = isDarkMode,
                onCheckedChange = { isNightMode ->
                    val mode = MODE_NIGHT_YES.takeIf { isNightMode } ?: MODE_NIGHT_NO

                    AppCompatDelegate.setDefaultNightMode(mode)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sunny),
                    contentDescription = stringResource(id = R.string.theme_mode),
                    tint = colors.onSurface
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
