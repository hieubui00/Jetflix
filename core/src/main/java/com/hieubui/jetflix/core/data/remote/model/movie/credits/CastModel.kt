package com.hieubui.jetflix.core.data.remote.model.movie.credits

import com.google.gson.annotations.SerializedName
import com.hieubui.jetflix.core.data.model.movie.credits.Cast

data class CastModel(
    @SerializedName("id")
    val castId: Int?,

    val name: String?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("profile_path")
    val avatarPath: String?
) {

    internal fun toData(): Cast = Cast(
        castId = this.castId,
        name = this.name,
        originalName = this.originalName,
        avatar = "https://image.tmdb.org/t/p/original${this.avatarPath}"
    )
}
