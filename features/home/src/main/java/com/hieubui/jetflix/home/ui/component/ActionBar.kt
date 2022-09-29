package com.hieubui.jetflix.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hieubui.jetflix.home.R
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
internal fun ActionBar(
    modifier: Modifier = Modifier,
    elevation: Dp = 8.dp,
) {
    Surface(
        modifier = modifier,
        elevation = elevation
    ) {
        Row(
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
                    contentDescription = stringResource(id = R.string.settings)
                )
            }

            Spacer(modifier = Modifier.weight(weight = 1f))

            Image(      // Logo
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.weight(weight = 1f))

            ThemeModeButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(size = 24.dp)
            )
        }
    }
}

@Composable
private fun ThemeModeButton(modifier: Modifier = Modifier) {
    var isDarkMode by rememberSaveable { mutableStateOf(false) }

    IconToggleButton(   // Theme Mode button
        modifier = modifier,
        checked = isDarkMode,
        onCheckedChange = { isDarkMode = it }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_sunny),
            contentDescription = stringResource(id = R.string.theme_mode)
        )
    }
}

@Preview
@Composable
private fun ActionBarPreview() {
    JetflixTheme {
        ActionBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 56.dp)
                .background(Color.White)
        )
    }
}