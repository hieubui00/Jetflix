package com.hieubui.jetflix.core.data.repository

import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Named

interface MovieRepository {

}

class MovieRepositoryImpl @Inject constructor(
    private val theMovieDatabaseService: TheMovieDatabaseService,

    @Named("io") private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

}