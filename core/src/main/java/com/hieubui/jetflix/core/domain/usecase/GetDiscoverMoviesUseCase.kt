package com.hieubui.jetflix.core.domain.usecase

import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.core.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named

class GetDiscoverMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,

    @Named("default") private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(
        page: Int = 1,
        language: String = Locale.getDefault().language
    ): List<Movie> = withContext(defaultDispatcher) {
        val discoverMovieModels = movieRepository.getDiscoverMovies(page, language)

        return@withContext discoverMovieModels.map { it.toData() }
    }
}