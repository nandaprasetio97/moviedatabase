package com.nandaprasetio.moviedatabase.core.data.datasource.database.entity

abstract class BaseMovieEntity {
    abstract val id: Int?
    abstract val movieId: Int
    abstract val page: Int
    abstract val title: String
    abstract val adult: Boolean
    abstract val backdropPath: String?
    abstract val originalLanguange: String
    abstract val originalTitle: String
    abstract val overview: String
    abstract val popularity: Float
    abstract val posterPath: String?
    abstract val releaseDate: String
    abstract val video: Boolean
    abstract val voteAverage: Float
    abstract val voteCount: Int
}