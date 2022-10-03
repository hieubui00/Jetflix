package com.hieubui.jetflix.util

import timber.log.Timber

class DebugTree : Timber.DebugTree() {
    private val LOG_FORMAT = "%s:%s"

    override fun createStackElementTag(element: StackTraceElement): String {
        return LOG_FORMAT.format(super.createStackElementTag(element), element.lineNumber)
    }
}
