package com.hieubui.jetflix.inject.component

import android.app.Application
import com.hieubui.jetflix.inject.module.NetworkModule
import com.hieubui.jetflix.inject.module.RepositoryModule
import com.hieubui.jetflix.inject.module.SubcomponentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        SubcomponentModule::class
    ]
)
interface ApplicationComponent {

    fun mainComponent(): MainComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}