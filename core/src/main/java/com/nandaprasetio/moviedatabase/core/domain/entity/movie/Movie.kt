package com.nandaprasetio.moviedatabase.core.domain.entity.movie

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

data class Movie(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("title")
    override val title: String,
    @SerializedName("adult")
    override val adult: Boolean,
    @SerializedName("backdrop_path")
    override val backdropPath: ImageUrlString?,
    @SerializedName("genre_ids")
    override val genreIds: List<Int>,
    @SerializedName("original_language")
    override val originalLanguange: String,
    @SerializedName("original_title")
    override val originalTitle: String,
    @SerializedName("overview")
    override val overview: String,
    @SerializedName("popularity")
    override val popularity: Float,
    @SerializedName("poster_path")
    override val posterPath: ImageUrlString?,
    @SerializedName("release_date")
    override val releaseDate: String,
    @SerializedName("video")
    override val video: Boolean,
    @SerializedName("vote_average")
    override val voteAverage: Float,
    @SerializedName("vote_count")
    override val voteCount: Int
): BaseMovie()