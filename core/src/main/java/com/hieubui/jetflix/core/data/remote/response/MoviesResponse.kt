package com.hieubui.jetflix.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.hieubui.jetflix.core.data.remote.model.movie.MovieModel

data class MoviesResponse(
    val page: Int?,

    val results: List<MovieModel>?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?,
)
