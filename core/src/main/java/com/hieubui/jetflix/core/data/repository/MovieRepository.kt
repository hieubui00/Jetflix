package com.hieubui.jetflix.core.data.repository

import com.hieubui.jetflix.core.data.remote.model.movie.DetailsModel
import com.hieubui.jetflix.core.data.remote.model.movie.MovieModel
import com.hieubui.jetflix.core.data.remote.model.movie.credits.CreditsModel
import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named

interface MovieRepository {

    suspend fun getDiscoverMovies(
        page: Int = 1,
        language: String = Locale.getDefault().language
    ): List<MovieModel>

    suspend fun getMovieDetails(
        movieId: Int,
        language: String = Locale.getDefault().language
    ): DetailsModel

    suspend fun getMovieCredits(
        movieId: Int,
        language: String = Locale.getDefault().language
    ): CreditsModel
}

class MovieRepositoryImpl @Inject constructor(
    private val service: TheMovieDatabaseService,

    @Named("io") private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun getDiscoverMovies(
        page: Int,
        language: String
    ): List<MovieModel> = withContext(ioDispatcher) {
        if (page < 1 || page > 500) {
            return@withContext listOf()
        }

        val response = service.getDiscoverMovies(page, language)

        return@withContext response.results ?: listOf()
    }

    override suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): DetailsModel = withContext(ioDispatcher) {
        val response = service.getMovieDetails(movieId, language)

        return@withContext response.toModel()
    }

    override suspend fun getMovieCredits(
        movieId: Int,
        language: String
    ): CreditsModel = withContext(ioDispatcher) {
        val response = service.getMovieCredits(movieId, language)

        return@withContext response.toModel()
    }
}
