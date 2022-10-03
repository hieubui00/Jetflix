package com.hieubui.jetflix.core.data.model.movie

import java.util.Date

data class Movie(
    val movieId: Int?,

    val title: String?,

    val releaseDate: Date?,

    val backdrop: String?,

    val poster: String?,

    val voteAverage: Float?,

    val voteCount: Int?
)
