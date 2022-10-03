package com.hieubui.jetflix.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCompanyModel(
    @SerializedName("productionCompanyId")
    val productionCompanyId: Int?,

    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?,

    @SerializedName("logo_path")
    val logoPath: String?,
)