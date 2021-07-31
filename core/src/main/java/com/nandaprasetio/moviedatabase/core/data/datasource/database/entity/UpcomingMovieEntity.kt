package com.nandaprasetio.moviedatabase.core.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nandaprasetio.moviedatabase.core.data.datasource.database.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_NAME_UPCOMING_MOVIE)
data class UpcomingMovieEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int? = null,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_MOVIE_ID)
    override val movieId: Int,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_PAGE)
    override val page: Int,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_TITLE)
    override val title: String,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_ADULT)
    override val adult: Boolean,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_BACKDROP_PATH)
    override val backdropPath: String?,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_ORIGINAL_LANGUANGE)
    override val originalLanguange: String,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_ORIGINAL_TITLE)
    override val originalTitle: String,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_OVERVIEW)
    override val overview: String,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_POPULARITY)
    override val popularity: Float,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_POSTER_PATH)
    override val posterPath: String?,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_RELEASE_DATE)
    override val releaseDate: String,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_VIDEO)
    override val video: Boolean,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_VOTE_AVERAGE)
    override val voteAverage: Float,
    @ColumnInfo(name = DatabaseContract.COLUMN_NAME_VOTE_COUNT)
    override val voteCount: Int
): BaseMovieEntity()