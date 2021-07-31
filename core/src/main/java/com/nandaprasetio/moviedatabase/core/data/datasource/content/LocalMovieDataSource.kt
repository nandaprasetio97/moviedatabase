package com.nandaprasetio.moviedatabase.core.data.datasource.content

import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie

interface LocalMovieDataSource {
    suspend fun getPopularMovieList(page: Int): PagingResult<Movie>
    suspend fun getTopRatedMovieList(page: Int): PagingResult<Movie>
    suspend fun getUpcomingMovieList(page: Int): PagingResult<Movie>
    suspend fun addPopularMovieListToDatabase(page: Int, pagingResult: PagingResult<Movie>): PagingResult<Movie>
    suspend fun addTopRatedMovieListToDatabase(page: Int, pagingResult: PagingResult<Movie>): PagingResult<Movie>
    suspend fun addUpcomingMovieListToDatabase(page: Int, pagingResult: PagingResult<Movie>): PagingResult<Movie>
    suspend fun deletePopularMovieListToDatabase(page: Int): Int
    suspend fun deleteTopRatedMovieListToDatabase(page: Int): Int
    suspend fun deleteUpcomingMovieListToDatabase(page: Int): Int
}