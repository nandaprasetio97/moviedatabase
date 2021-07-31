package com.nandaprasetio.moviedatabase.core.ext

import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.BaseMovieEntity
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.PopularMovieEntity
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.TopRatedMovieEntity
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.UpcomingMovieEntity
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

fun BaseMovieEntity.mapToMovie(): Movie {
    return Movie(
        id = this.movieId,
        title = this.title,
        adult = this.adult,
        backdropPath = ImageUrlString(this.backdropPath),
        genreIds = listOf(),
        originalLanguange = this.originalLanguange,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = ImageUrlString(this.posterPath),
        releaseDate = this.releaseDate ?: "",
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

inline fun<reified T: BaseMovieEntity> Movie.mapToDesiredMovieEntity(page: Int): T {
    return when (T::class) {
        PopularMovieEntity::class -> PopularMovieEntity(
            movieId = this.id,
            page = page,
            title = this.title,
            adult = this.adult,
            backdropPath = this.backdropPath?.toIdUrl(),
            originalLanguange = this.originalLanguange,
            originalTitle = this.originalTitle,
            overview = this.overview,
            popularity = this.popularity,
            posterPath = this.posterPath?.toIdUrl(),
            releaseDate = this.releaseDate ?: "",
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        ) as T
        TopRatedMovieEntity::class -> TopRatedMovieEntity(
            movieId = this.id,
            page = page,
            title = this.title,
            adult = this.adult,
            backdropPath = this.backdropPath?.toIdUrl(),
            originalLanguange = this.originalLanguange,
            originalTitle = this.originalTitle,
            overview = this.overview,
            popularity = this.popularity,
            posterPath = this.posterPath?.toIdUrl(),
            releaseDate = this.releaseDate ?: "",
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        ) as T
        UpcomingMovieEntity::class -> UpcomingMovieEntity(
            movieId = this.id,
            page = page,
            title = this.title,
            adult = this.adult,
            backdropPath = this.backdropPath?.toIdUrl(),
            originalLanguange = this.originalLanguange,
            originalTitle = this.originalTitle,
            overview = this.overview,
            popularity = this.popularity,
            posterPath = this.posterPath?.toIdUrl(),
            releaseDate = this.releaseDate ?: "",
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        ) as T
        else -> throw IllegalStateException("Invalid type")
    }
}