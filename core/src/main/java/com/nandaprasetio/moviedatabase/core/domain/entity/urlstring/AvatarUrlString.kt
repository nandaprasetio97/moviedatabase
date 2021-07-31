package com.nandaprasetio.moviedatabase.core.domain.entity.urlstring

data class AvatarUrlString(private val avatarUrl: String?) {
    override fun toString(): String {
        return avatarUrl?.substring(1) ?: ""
    }
}