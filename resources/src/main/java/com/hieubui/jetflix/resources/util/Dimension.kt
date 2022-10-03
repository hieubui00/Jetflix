package com.hieubui.jetflix.resources.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

val Dp.px: Float
    @Composable
    get() {
        val density = LocalDensity.current.density

        return remember(this) { this.value * density }
    }