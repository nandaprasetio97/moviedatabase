package com.nandaprasetio.moviedatabase.core.domain.entity.people

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

data class Crew(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("adult")
    override val adult: Boolean,
    @SerializedName("gender")
    override val gender: Int,
    @SerializedName("known_for_department")
    override val knownForDepartment: String,
    @SerializedName("name")
    override val name: String,
    @SerializedName("original_name")
    override val originalName: String,
    @SerializedName("popularity")
    override val popularity: Float,
    @SerializedName("profile_path")
    override val profilePath: ImageUrlString?,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("job")
    val job: String
): BasePeople()