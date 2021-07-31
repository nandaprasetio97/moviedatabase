package com.nandaprasetio.moviedatabase.core.domain.entity

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

data class ProductionCompany(
    @SerializedName("id")
    val id: String,
    @SerializedName("logo_path")
    val logoPath: ImageUrlString?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)