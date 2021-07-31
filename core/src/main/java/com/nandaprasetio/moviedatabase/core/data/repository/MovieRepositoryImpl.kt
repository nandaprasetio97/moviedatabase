package com.nandaprasetio.moviedatabase.core.data.repository

import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSource
import com.nandaprasetio.moviedatabase.core.di.module.IODispatcher
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.GenreList
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetailCredit
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieUserReview
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage.MovieDetailImage
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailvideo.MovieDetailVideo
import com.nandaprasetio.moviedatabase.core.ext.getHttpRequestLoadDataResult
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDataSource: MovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource
): MovieRepository {
    override suspend fun getMovieGenreList(page: Int): LoadDataResult<GenreList> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getMovieGenreList(page) }
    }

    override suspend fun getPopularMovieList(page: Int): LoadDataResult<PagingResult<Movie>> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getPopularMovieList(page) }.let {
            when(it) {
                is LoadDataResult.Failed -> getHttpRequestLoadDataResult(coroutineDispatcher) {
                    localMovieDataSource.getPopularMovieList(page)
                }
                is LoadDataResult.Success -> getHttpRequestLoadDataResult(coroutineDispatcher) {
                    localMovieDataSource.deletePopularMovieListToDatabase(page)
                    localMovieDataSource.addPopularMovieListToDatabase(page, it.value)
                }.let { result -> if (result is LoadDataResult.Success) { it } else { result } }
            }
        }
    }

    override suspend fun getTopRatedMovieList(page: Int): LoadDataResult<PagingResult<Movie>> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getTopRatedMovieList(page) }.let {
            when(it) {
                is LoadDataResult.Failed -> getHttpRequestLoadDataResult(coroutineDispatcher) {
                    localMovieDataSource.getTopRatedMovieList(page)
                }
                is LoadDataResult.Success -> getHttpRequestLoadDataResult(coroutineDispatcher) {
                    localMovieDataSource.deleteTopRatedMovieListToDatabase(page)
                    localMovieDataSource.addTopRatedMovieListToDatabase(page, it.value)
                }.let { result -> if (result is LoadDataResult.Success) { it } else { result } }
            }
        }
    }

    override suspend fun getUpcomingMovieList(page: Int): LoadDataResult<PagingResult<Movie>> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getUpcomingMovieList(page) }.let {
            when(it) {
                is LoadDataResult.Failed -> getHttpRequestLoadDataResult(coroutineDispatcher) {
                    localMovieDataSource.getUpcomingMovieList(page)
                }
                is LoadDataResult.Success -> getHttpRequestLoadDataResult(coroutineDispatcher) {
                    localMovieDataSource.deleteUpcomingMovieListToDatabase(page)
                    localMovieDataSource.addUpcomingMovieListToDatabase(page, it.value)
                }.let { result -> if (result is LoadDataResult.Success) { it } else { result } }
            }
        }
    }

    override suspend fun getMovieDetail(movieId: Int): LoadDataResult<MovieDetail> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getMovieDetail(movieId) }
    }

    override suspend fun getMovieDetailImage(movieId: Int): LoadDataResult<MovieDetailImage> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getMovieDetailImage(movieId) }
    }

    override suspend fun getMovieDetailVideo(movieId: Int): LoadDataResult<MovieDetailVideo> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getMovieDetailVideo(movieId) }
    }

    override suspend fun getMovieDetailCredit(movieId: Int): LoadDataResult<MovieDetailCredit> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getMovieDetailCredit(movieId) }
    }

    override suspend fun getMovieRecommendation(movieId: Int, page: Int): LoadDataResult<PagingResult<Movie>> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getMovieRecommendation(movieId, page) }
    }

    override suspend fun getMovieUserReview(movieId: Int): LoadDataResult<PagingResult<MovieUserReview>> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getMovieUserReview(movieId) }
    }

    override suspend fun getDiscoveredMovieBasedGenre(page: Int, genreId: Int): LoadDataResult<PagingResult<Movie>> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.getDiscoveredMovieBasedGenre(page, genreId) }
    }

    override suspend fun searchMovie(page: Int, query: String): LoadDataResult<PagingResult<Movie>> {
        return getHttpRequestLoadDataResult(coroutineDispatcher) { movieDataSource.searchMovie(page, query) }
    }
}