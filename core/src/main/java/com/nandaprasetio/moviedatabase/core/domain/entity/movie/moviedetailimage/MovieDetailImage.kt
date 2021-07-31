package com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage

import com.google.gson.annotations.SerializedName

data class MovieDetailImage(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrops")
    val backdrops: List<MovieDetailImageAttribute>,
    @SerializedName("posters")
    val posters: List<MovieDetailImageAttribute>
)