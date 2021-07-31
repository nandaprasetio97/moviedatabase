package com.nandaprasetio.moviedatabase.core.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nandaprasetio.moviedatabase.core.data.datasource.database.DatabaseContract
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.PopularMovieEntity

@Dao
interface PopularMovieDao {
    @Query("SELECT COUNT(*) FROM ${DatabaseContract.TABLE_NAME_POPULAR_MOVIE}")
    fun getPopularMovieCount(): Int

    @Query("SELECT * FROM ${DatabaseContract.TABLE_NAME_POPULAR_MOVIE} WHERE ${DatabaseContract.COLUMN_NAME_PAGE} = :page")
    fun getPopularMovie(page: Int): List<PopularMovieEntity>

    @Insert
    fun insertPopularMovie(vararg popularMovieEntityList: PopularMovieEntity)

    @Query("DELETE FROM ${DatabaseContract.TABLE_NAME_POPULAR_MOVIE}  WHERE ${DatabaseContract.COLUMN_NAME_PAGE} = :page")
    fun deletePopularMovie(page: Int): Int

    @Query("DELETE FROM ${DatabaseContract.TABLE_NAME_POPULAR_MOVIE}")
    fun deletePopularMovie(): Int
}