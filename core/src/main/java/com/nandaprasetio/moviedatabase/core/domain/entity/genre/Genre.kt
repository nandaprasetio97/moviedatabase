package com.nandaprasetio.moviedatabase.core.domain.entity.genre

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)