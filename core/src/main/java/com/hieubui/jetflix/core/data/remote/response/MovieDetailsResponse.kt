package com.hieubui.jetflix.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.hieubui.jetflix.core.data.remote.model.GenreModel
import com.hieubui.jetflix.core.data.remote.model.ProductionCompanyModel
import java.util.Date

data class MovieDetailsResponse(
    @SerializedName("id")
    val movieId: Int?,

    val title: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("runtime")
    val duration: Int?,

    val genres: List<GenreModel>?,

    @SerializedName("release_date")
    val releaseDate: Date?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyModel>?,

    val overview: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Float?,

    @SerializedName("vote_count")
    val voteCount: Int
)