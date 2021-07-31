package com.nandaprasetio.moviedatabase.core.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nandaprasetio.moviedatabase.core.data.datasource.database.DatabaseContract
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.UpcomingMovieEntity

@Dao
interface UpcomingMovieDao {
    @Query("SELECT COUNT(*) FROM ${DatabaseContract.TABLE_NAME_UPCOMING_MOVIE}")
    fun getUpcomingMovieCount(): Int

    @Query("SELECT * FROM ${DatabaseContract.TABLE_NAME_UPCOMING_MOVIE} WHERE ${DatabaseContract.COLUMN_NAME_PAGE} = :page")
    fun getUpcomingMovie(page: Int): List<UpcomingMovieEntity>

    @Insert
    fun insertUpcomingMovie(vararg upcomingMovieEntityList: UpcomingMovieEntity)

    @Query("DELETE FROM ${DatabaseContract.TABLE_NAME_UPCOMING_MOVIE} WHERE ${DatabaseContract.COLUMN_NAME_PAGE} = :page")
    fun deleteUpcomingMovie(page: Int): Int

    @Query("DELETE FROM ${DatabaseContract.TABLE_NAME_UPCOMING_MOVIE}")
    fun deleteUpcomingMovie(): Int
}