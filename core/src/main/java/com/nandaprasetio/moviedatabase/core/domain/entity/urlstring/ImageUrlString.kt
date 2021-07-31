package com.nandaprasetio.moviedatabase.core.domain.entity.urlstring

import com.nandaprasetio.moviedatabase.core.BuildConfig

data class ImageUrlString(private val idImageUrlPathSegment: String?) {
    override fun toString(): String {
        return "${BuildConfig.BASE_IMAGE_URL}w200${idImageUrlPathSegment}"
    }

    fun toStringBasedSizeExpression(sizeExpression: String?): String {
        return "${BuildConfig.BASE_IMAGE_URL}${sizeExpression}${idImageUrlPathSegment}"
    }

    fun toIdUrl(): String? {
        return idImageUrlPathSegment
    }
}