package com.nandaprasetio.moviedatabase.core.domain.entity.genre

import com.google.gson.annotations.SerializedName

data class GenreList(
    @SerializedName("genres")
    val genres: List<Genre>
)