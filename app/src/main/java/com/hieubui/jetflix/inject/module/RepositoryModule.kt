package com.hieubui.jetflix.inject.module

import com.hieubui.jetflix.core.data.repository.MovieRepository
import com.hieubui.jetflix.core.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DispatcherModule::class
    ]
)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}