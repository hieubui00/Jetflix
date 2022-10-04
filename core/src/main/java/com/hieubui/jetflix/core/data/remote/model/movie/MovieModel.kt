package com.hieubui.jetflix.core.data.remote.model.movie

import com.google.gson.annotations.SerializedName
import com.hieubui.jetflix.core.data.model.movie.Movie
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
) {

    internal fun toData(): Movie = Movie(
        movieId = this.movieId,
        title = this.title,
        releaseDate = this.releaseDate,
        backdrop = "https://image.tmdb.org/t/p/original${this.backdropPath}",
        poster = "https://image.tmdb.org/t/p/w342${this.posterPath}",
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}
