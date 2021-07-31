package com.nandaprasetio.moviedatabase.core.domain.entity

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.AvatarUrlString

data class AuthorDetail(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar_path")
    val avatarPath: AvatarUrlString?,
    @SerializedName("rating")
    val rating: Float?
)