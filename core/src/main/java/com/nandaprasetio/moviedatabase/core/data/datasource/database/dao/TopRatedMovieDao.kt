package com.nandaprasetio.moviedatabase.core.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nandaprasetio.moviedatabase.core.data.datasource.database.DatabaseContract
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.TopRatedMovieEntity

@Dao
interface TopRatedMovieDao {
    @Query("SELECT COUNT(*) FROM ${DatabaseContract.TABLE_NAME_TOP_RATED_MOVIE}")
    fun getTopRatedMovieCount(): Int

    @Query("SELECT * FROM ${DatabaseContract.TABLE_NAME_TOP_RATED_MOVIE} WHERE ${DatabaseContract.COLUMN_NAME_PAGE} = :page")
    fun getTopRatedMovie(page: Int): List<TopRatedMovieEntity>

    @Insert
    fun insertTopRatedMovie(vararg topRatedMovieEntityList: TopRatedMovieEntity)

    @Query("DELETE FROM ${DatabaseContract.TABLE_NAME_TOP_RATED_MOVIE} WHERE ${DatabaseContract.COLUMN_NAME_PAGE} = :page")
    fun deleteTopRatedMovie(page: Int): Int

    @Query("DELETE FROM ${DatabaseContract.TABLE_NAME_TOP_RATED_MOVIE}")
    fun deleteTopRatedMovie(): Int
}