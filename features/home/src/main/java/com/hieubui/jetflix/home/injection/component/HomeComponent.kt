package com.hieubui.jetflix.home.injection.component

import com.hieubui.jetflix.home.injection.scope.HomeScope
import com.hieubui.jetflix.home.ui.HomeFragment
import com.hieubui.jetflix.inject.component.MainComponent
import dagger.Component

@HomeScope
@Component(dependencies = [MainComponent::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)
}