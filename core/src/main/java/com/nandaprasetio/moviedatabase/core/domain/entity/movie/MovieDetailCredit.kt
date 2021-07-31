package com.nandaprasetio.moviedatabase.core.domain.entity.movie

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.moviedatabase.core.domain.entity.people.Cast
import com.nandaprasetio.moviedatabase.core.domain.entity.people.Crew

class MovieDetailCredit(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>
)