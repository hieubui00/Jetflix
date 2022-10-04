package com.hieubui.jetflix.core.data.remote.model.movie.credits

import com.hieubui.jetflix.core.data.model.movie.credits.Credit

data class CreditsModel(
    val creditId: Int?,

    val casts: List<CastModel>?,

    val crews: List<CrewModel>?
) {

    internal fun toData(): Credit = Credit(
        casts = casts?.map { it.toData() },
        crews = crews?.map { it.toData() }
    )
}
