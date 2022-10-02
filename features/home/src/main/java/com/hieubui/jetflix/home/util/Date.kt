package com.hieubui.jetflix.home.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toString(pattern: String, locale: Locale = Locale.getDefault()): String {
    return SimpleDateFormat(pattern, locale).format(this)
}