package com.nandaprasetio.moviedatabase.core.data.datasource.database


object DatabaseContract {
    const val DATABASE_NAME = "MovieCatalogDatabase"

    const val TABLE_NAME_POPULAR_MOVIE = "popular_movie"
    const val TABLE_NAME_TOP_RATED_MOVIE = "top_rated_movie"
    const val TABLE_NAME_UPCOMING_MOVIE = "upcoming_movie"

    const val COLUMN_NAME_MOVIE_ID = "movie_id"
    const val COLUMN_NAME_PAGE = "page"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_ADULT = "adult"
    const val COLUMN_NAME_BACKDROP_PATH = "backdrop_path"
    const val COLUMN_NAME_ORIGINAL_LANGUANGE = "original_language"
    const val COLUMN_NAME_ORIGINAL_TITLE = "original_title"
    const val COLUMN_NAME_OVERVIEW = "overview"
    const val COLUMN_NAME_POPULARITY = "popularity"
    const val COLUMN_NAME_POSTER_PATH = "poster_path"
    const val COLUMN_NAME_RELEASE_DATE = "release_date"
    const val COLUMN_NAME_VIDEO = "video"
    const val COLUMN_NAME_VOTE_AVERAGE = "vote_average"
    const val COLUMN_NAME_VOTE_COUNT = "vote_count"
}