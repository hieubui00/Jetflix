package com.hieubui.jetflix.core.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MovieModel(
    @SerializedName("id")
    val movieId: Int?,

    val title: String?,

    @SerializedName("release_date")
    val releaseDate: Date?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Float?,

    @SerializedName("vote_count")
    val voteCount: Int
)
