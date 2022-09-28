package com.hieubui.jetflix.inject.component

import android.content.Context
import com.hieubui.jetflix.inject.module.RepositoryModule
import com.hieubui.jetflix.inject.scope.MainScope
import com.hieubui.jetflix.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@MainScope
@Subcomponent
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): MainComponent
    }
}