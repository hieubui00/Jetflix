package com.hieubui.jetflix.home.injection.component

import com.hieubui.jetflix.home.injection.scope.HomeScope
import com.hieubui.jetflix.home.ui.HomeFragment
import com.hieubui.jetflix.inject.component.MainComponent
import com.hieubui.jetflix.inject.module.DispatcherModule
import dagger.Component

@HomeScope
@Component(
    dependencies = [MainComponent::class],
    modules = [DispatcherModule::class]
)
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)
}
