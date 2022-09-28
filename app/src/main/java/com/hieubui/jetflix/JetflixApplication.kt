package com.hieubui.jetflix

import android.app.Application
import com.hieubui.jetflix.inject.component.ApplicationComponent
import com.hieubui.jetflix.inject.component.DaggerApplicationComponent
import com.hieubui.jetflix.util.DebugTree
import timber.log.Timber

class JetflixApplication : Application() {
    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        component = DaggerApplicationComponent.factory().create(this)
    }
}