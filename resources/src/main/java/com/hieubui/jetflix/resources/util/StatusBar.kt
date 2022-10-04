package com.hieubui.jetflix.resources.util

import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment

fun Fragment.setLightStatusBar(isLightStatusBar: Boolean) {
    val window = requireActivity().window
    val view = requireView()

    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isLightStatusBar
}
