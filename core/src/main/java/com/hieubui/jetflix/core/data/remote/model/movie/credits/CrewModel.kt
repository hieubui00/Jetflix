package com.hieubui.jetflix.core.data.remote.model.movie.credits

import com.google.gson.annotations.SerializedName
import com.hieubui.jetflix.core.data.model.movie.credits.Crew

data class CrewModel(
    @SerializedName("id")
    val crewId: Int?,

    val name: String?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("profile_path")
    val avatarPath: String?,

    val job: String?
) {

    internal fun toData(): Crew = Crew(
        crewId = this.crewId,
        name = this.name,
        originalName = this.originalName,
        avatar = "https://image.tmdb.org/t/p/original${this.avatarPath}",
        job = this.job
    )
}
