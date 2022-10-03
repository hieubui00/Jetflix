package com.hieubui.jetflix.core.data.repository

import com.hieubui.jetflix.core.data.model.Genre
import com.hieubui.jetflix.core.data.model.ProductionCompany
import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.core.data.model.movie.MovieDetails
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
    ): List<Movie>

    suspend fun getMovieDetails(
        movieId: Int,
        language: String = Locale.getDefault().language
    ): MovieDetails
}

class MovieRepositoryImpl @Inject constructor(
    private val theMovieDatabaseService: TheMovieDatabaseService,

    @Named("io") private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun getDiscoverMovies(
        page: Int,
        language: String
    ): List<Movie> = withContext(dispatcher) {
        if (page < 1 || page > 500) {
            return@withContext listOf()
        }

        val response = theMovieDatabaseService.getDiscoverMovies(page, language)

        return@withContext response.results?.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                releaseDate = it.releaseDate,
                backdrop = "https://image.tmdb.org/t/p/original${it.backdropPath}",
                poster = "https://image.tmdb.org/t/p/w342${it.posterPath}",
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        } ?: listOf()
    }

    override suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): MovieDetails = withContext(dispatcher) {
        val response = theMovieDatabaseService.getMovieDetails(movieId, language)
        val genre = response.genres?.map {
            Genre(
                genreId = it.genreId,
                name = it.name
            )
        }
        val productionCompanies = response.productionCompanies?.map {
            ProductionCompany(
                productionCompanyId = it.productionCompanyId,
                name = it.name,
                originCountry = it.originCountry,
                logo = "https://image.tmdb.org/t/p/original${it.logoPath}",
            )
        }

        return@withContext MovieDetails(
            movieId = response.movieId,
            title = response.title,
            originalTitle = response.originalTitle,
            duration = response.duration,
            genres = genre,
            releaseDate = response.releaseDate,
            productionCompanies = productionCompanies,
            overview = response.overview,
            backdrop = "https://image.tmdb.org/t/p/original${response.backdropPath}",
            poster = "https://image.tmdb.org/t/p/w342${response.posterPath}",
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
    }
}
