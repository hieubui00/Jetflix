package com.hieubui.jetflix.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.hieubui.jetflix.core.data.remote.model.movie.credits.CastModel
import com.hieubui.jetflix.core.data.remote.model.movie.credits.CreditsModel
import com.hieubui.jetflix.core.data.remote.model.movie.credits.CrewModel

data class MovieCreditsResponse(
    val creditId: Int?,

    @SerializedName("cast")
    val casts: List<CastModel>?,

    @SerializedName("crew")
    val crews: List<CrewModel>?
) {

    internal fun toModel(): CreditsModel = CreditsModel(
        creditId = this.creditId,
        casts = this.casts,
        crews = this.crews,
    )
}
