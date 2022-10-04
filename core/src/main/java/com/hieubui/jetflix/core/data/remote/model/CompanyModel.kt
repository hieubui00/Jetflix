package com.hieubui.jetflix.core.data.remote.model

import com.google.gson.annotations.SerializedName
import com.hieubui.jetflix.core.data.model.Company

data class CompanyModel(
    @SerializedName("id")
    val companyId: Int?,

    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?,

    @SerializedName("logo_path")
    val logoPath: String?,
) {

    internal fun toData(): Company = Company(
        companyId = this.companyId,
        name = this.name,
        originCountry = this.originCountry,
        logo = "https://image.tmdb.org/t/p/original${this.logoPath}",
    )
}
