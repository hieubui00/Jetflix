package com.hieubui.jetflix.movie.details.inject.component

import androidx.lifecycle.SavedStateHandle
import com.hieubui.jetflix.inject.component.MainComponent
import com.hieubui.jetflix.inject.module.DispatcherModule
import com.hieubui.jetflix.movie.details.inject.scope.MovieDetailsScope
import com.hieubui.jetflix.movie.details.ui.MovieDetailsFragment
import dagger.BindsInstance
import dagger.Component

@MovieDetailsScope
@Component(
    dependencies = [MainComponent::class],
    modules = [DispatcherModule::class]
)
interface MovieDetailsComponent {

    fun inject(movieDetailsFragment: MovieDetailsFragment)

    @Component.Builder
    interface Builder {

        fun mainComponent(mainComponent: MainComponent): Builder

        fun savedStateHandle(@BindsInstance savedStateHandle: SavedStateHandle): Builder

        fun build(): MovieDetailsComponent
    }
}
