package com.hieubui.jetflix

import android.app.Application
import com.hieubui.jetflix.util.DebugTree
import timber.log.Timber

class JetflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}