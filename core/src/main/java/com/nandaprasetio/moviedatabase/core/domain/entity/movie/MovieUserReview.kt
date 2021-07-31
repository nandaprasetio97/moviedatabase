package com.nandaprasetio.moviedatabase.core.domain.entity.movie

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.moviedatabase.core.domain.entity.AuthorDetail

data class MovieUserReview(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("author_details")
    val authorDetail: AuthorDetail,
    @SerializedName("content")
    val content: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("url")
    val url: String,
)