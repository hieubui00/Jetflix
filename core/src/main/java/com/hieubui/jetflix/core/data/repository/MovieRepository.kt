package com.hieubui.jetflix.core.data.repository

import com.hieubui.jetflix.core.data.model.Movie
import com.hieubui.jetflix.core.data.remote.model.asData
import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

interface MovieRepository {

    suspend fun getDiscoverMovies(page: Int = 1): List<Movie>
}

class MovieRepositoryImpl @Inject constructor(
    private val theMovieDatabaseService: TheMovieDatabaseService,

    @Named("io") private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun getDiscoverMovies(page: Int): List<Movie> = withContext(dispatcher) {
        if (page < 1 || page > 500) {
            return@withContext listOf()
        }

        val response = theMovieDatabaseService.getDiscoverMovies(page)

        return@withContext response.results?.map { it.asData() } ?: listOf()
    }
}