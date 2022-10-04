package com.hieubui.jetflix.core.data.remote.model.movie

import com.hieubui.jetflix.core.data.remote.model.CompanyModel
import com.hieubui.jetflix.core.data.remote.model.GenreModel
import java.util.Date

data class DetailsModel(
    val movieId: Int?,

    val title: String?,

    val originalTitle: String?,

    val duration: Int?,

    val genres: List<GenreModel>?,

    val releaseDate: Date?,

    val productionCompanies: List<CompanyModel>?,

    val overview: String?,

    val backdropPath: String?,

    val posterPath: String?,

    val voteAverage: Float?,

    val voteCount: Int?
)
