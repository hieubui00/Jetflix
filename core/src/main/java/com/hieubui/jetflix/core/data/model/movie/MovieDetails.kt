package com.hieubui.jetflix.core.data.model.movie

import com.hieubui.jetflix.core.data.model.Genre
import com.hieubui.jetflix.core.data.remote.model.ProductionCompanyModel
import java.util.Date

data class MovieDetails(
    val movieId: Int?,

    val title: String?,

    val originalTitle: String?,

    val duration: Int?,

    val genres: List<Genre>?,

    val releaseDate: Date?,

    val productionCompanies: List<ProductionCompanyModel>?,

    val overview: String?,

    val backdrop: String?,

    val poster: String?,

    val voteAverage: Float?,

    val voteCount: Int?
)