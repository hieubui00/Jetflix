package com.hieubui.jetflix.core.data.model.movie

import com.hieubui.jetflix.core.data.model.Company
import com.hieubui.jetflix.core.data.model.Genre
import com.hieubui.jetflix.core.data.model.movie.credits.Credit
import java.util.Date

data class MovieDetails(
    val movieId: Int?,

    val title: String?,

    val originalTitle: String?,

    val duration: Int?,

    val genres: List<Genre>?,

    val releaseDate: Date?,

    val productionCompanies: List<Company>?,

    val overview: String?,

    val backdrop: String?,

    val poster: String?,

    val credits: Credit?,

    val voteAverage: Float?,

    val voteCount: Int?
)
