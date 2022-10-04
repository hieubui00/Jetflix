package com.hieubui.jetflix.core.domain.usecase

import com.hieubui.jetflix.core.data.model.movie.MovieDetails
import com.hieubui.jetflix.core.data.remote.model.movie.DetailsModel
import com.hieubui.jetflix.core.data.remote.model.movie.credits.CreditsModel
import com.hieubui.jetflix.core.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,

    @Named("default") private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(
        movieId: Int,
        language: String = Locale.getDefault().language
    ): MovieDetails = withContext(defaultDispatcher) {
        val detailsModel = async { movieRepository.getMovieDetails(movieId, language) }
        val creditsModel = async { movieRepository.getMovieCredits(movieId, language) }

        return@withContext map(detailsModel.await(), creditsModel.await())
    }

    private fun map(detailsModel: DetailsModel, creditsModel: CreditsModel): MovieDetails {
        val genres = detailsModel.genres?.map { it.toData() }
        val productionCompanies = detailsModel.productionCompanies?.map { it.toData() }

        return MovieDetails(
            movieId = detailsModel.movieId,
            title = detailsModel.title,
            originalTitle = detailsModel.originalTitle,
            duration = detailsModel.duration,
            genres = genres,
            releaseDate = detailsModel.releaseDate,
            productionCompanies = productionCompanies,
            overview = detailsModel.overview,
            backdrop = "https://image.tmdb.org/t/p/original${detailsModel.backdropPath}",
            poster = "https://image.tmdb.org/t/p/w342${detailsModel.posterPath}",
            credits = creditsModel.toData(),
            voteAverage = detailsModel.voteAverage,
            voteCount = detailsModel.voteCount
        )
    }
}
