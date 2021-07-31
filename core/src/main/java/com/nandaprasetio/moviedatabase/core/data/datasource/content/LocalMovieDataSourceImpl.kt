package com.nandaprasetio.moviedatabase.core.data.datasource.content

import com.nandaprasetio.moviedatabase.core.data.datasource.database.dao.PopularMovieDao
import com.nandaprasetio.moviedatabase.core.data.datasource.database.dao.TopRatedMovieDao
import com.nandaprasetio.moviedatabase.core.data.datasource.database.dao.UpcomingMovieDao
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.PopularMovieEntity
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.TopRatedMovieEntity
import com.nandaprasetio.moviedatabase.core.data.datasource.database.entity.UpcomingMovieEntity
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.ext.mapToDesiredMovieEntity
import com.nandaprasetio.moviedatabase.core.ext.mapToMovie
import java.io.IOException
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(
    private val popularMovieDao: PopularMovieDao,
    private val topRatedMovieDao: TopRatedMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao
): LocalMovieDataSource {
    override suspend fun getPopularMovieList(page: Int): PagingResult<Movie> {
        return popularMovieDao.getPopularMovie(page).map { it.mapToMovie() }.let {
            PagingResult(
                page = if (it.isNotEmpty()) { 0 } else { 1 },
                results = it,
                totalPages = 1,
                totalResults = it.size
            )
        }
    }

    override suspend fun getTopRatedMovieList(page: Int): PagingResult<Movie> {
        return topRatedMovieDao.getTopRatedMovie(page).map { it.mapToMovie() }.let {
            PagingResult(
                page = if (it.isNotEmpty()) { 0 } else { 1 },
                results = it,
                totalPages = 1,
                totalResults = it.size
            )
        }
    }

    override suspend fun getUpcomingMovieList(page: Int): PagingResult<Movie> {
        return upcomingMovieDao.getUpcomingMovie(page).map { it.mapToMovie() }.let {
            PagingResult(
                page = if (it.isNotEmpty()) { 0 } else { 1 },
                results = it,
                totalPages = 1,
                totalResults = it.size
            )
        }
    }

    override suspend fun addPopularMovieListToDatabase(page: Int, pagingResult: PagingResult<Movie>): PagingResult<Movie> {
        return popularMovieDao.insertPopularMovie(
            *pagingResult.results.map { it.mapToDesiredMovieEntity<PopularMovieEntity>(page) }.toTypedArray()
        ).let { pagingResult }
    }

    override suspend fun addTopRatedMovieListToDatabase(page: Int, pagingResult: PagingResult<Movie>): PagingResult<Movie> {
        return topRatedMovieDao.insertTopRatedMovie(
            *pagingResult.results.map { it.mapToDesiredMovieEntity<TopRatedMovieEntity>(page) }.toTypedArray()
        ).let { pagingResult }
    }

    override suspend fun addUpcomingMovieListToDatabase(page: Int, pagingResult: PagingResult<Movie>): PagingResult<Movie> {
        return upcomingMovieDao.insertUpcomingMovie(
            *pagingResult.results.map { it.mapToDesiredMovieEntity<UpcomingMovieEntity>(page) }.toTypedArray()
        ).let { pagingResult }
    }

    override suspend fun deletePopularMovieListToDatabase(page: Int): Int {
        return popularMovieDao.deletePopularMovie(page)
    }

    override suspend fun deleteTopRatedMovieListToDatabase(page: Int): Int {
        return topRatedMovieDao.deleteTopRatedMovie(page)
    }

    override suspend fun deleteUpcomingMovieListToDatabase(page: Int): Int {
        return upcomingMovieDao.deleteUpcomingMovie(page)
    }
}