package com.nandaprasetio.moviedatabase.core.domain.entity.movie

import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

abstract class BaseMovie {
    abstract val id: Int
    abstract val title: String
    abstract val adult: Boolean
    abstract val backdropPath: ImageUrlString?
    abstract val genreIds: List<Int>
    abstract val originalLanguange: String
    abstract val originalTitle: String
    abstract val overview: String
    abstract val popularity: Float
    abstract val posterPath: ImageUrlString?
    abstract val releaseDate: String
    abstract val video: Boolean
    abstract val voteAverage: Float
    abstract val voteCount: Int
}