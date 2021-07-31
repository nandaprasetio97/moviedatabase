package com.nandaprasetio.moviedatabase.core.domain.entity

import com.google.gson.annotations.SerializedName

data class PagingResult<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)