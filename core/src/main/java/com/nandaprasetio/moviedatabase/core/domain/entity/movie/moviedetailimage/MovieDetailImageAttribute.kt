package com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

data class MovieDetailImageAttribute(
    @SerializedName("aspect_ratio")
    val aspectRatio: Float,
    @SerializedName("height")
    val height: Int,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("file_path")
    val filePath: ImageUrlString,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("width")
    val width: Int
)