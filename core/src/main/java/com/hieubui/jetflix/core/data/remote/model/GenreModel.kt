package com.hieubui.jetflix.core.data.remote.model

import com.hieubui.jetflix.core.data.model.Genre

data class GenreModel(
    val genreId: Int?,

    val name: String?
) {

    internal fun toData(): Genre = Genre(
        genreId = this.genreId,
        name = this.name
    )
}
