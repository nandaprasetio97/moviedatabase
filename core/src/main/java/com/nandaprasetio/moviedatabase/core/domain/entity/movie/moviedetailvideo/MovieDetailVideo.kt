package com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailvideo

import com.google.gson.annotations.SerializedName

data class MovieDetailVideo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<MovieDetailVideoAttribute>
)