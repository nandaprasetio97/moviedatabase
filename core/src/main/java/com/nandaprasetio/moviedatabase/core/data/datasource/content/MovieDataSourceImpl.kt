package com.nandaprasetio.moviedatabase.core.data.datasource.content

import com.nandaprasetio.moviedatabase.core.data.service.MovieService
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.GenreList
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetailCredit
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieUserReview
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage.MovieDetailImage
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailvideo.MovieDetailVideo
import com.nandaprasetio.moviedatabase.core.ext.getHttpRequestResult
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieService: MovieService
): MovieDataSource {
    override suspend fun getMovieGenreList(page: Int): GenreList {
        return getHttpRequestResult { movieService.getMovieGenreList(page) }
    }

    override suspend fun getPopularMovieList(page: Int): PagingResult<Movie> {
        return getHttpRequestResult { movieService.getPopularMovieList(page) }
    }

    override suspend fun getTopRatedMovieList(page: Int): PagingResult<Movie> {
        return getHttpRequestResult { movieService.getTopRatedMovieList(page) }
    }

    override suspend fun getUpcomingMovieList(page: Int): PagingResult<Movie> {
        return getHttpRequestResult { movieService.getUpcomingMovieList(page) }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return getHttpRequestResult { movieService.getMovieDetail(movieId) }
    }

    override suspend fun getMovieDetailImage(movieId: Int): MovieDetailImage {
        return getHttpRequestResult { movieService.getMovieDetailImage(movieId) }
    }

    override suspend fun getMovieDetailVideo(movieId: Int): MovieDetailVideo {
        return getHttpRequestResult { movieService.getMovieDetailVideo(movieId) }
    }

    override suspend fun getMovieDetailCredit(movieId: Int): MovieDetailCredit {
        return getHttpRequestResult { movieService.getMovieDetailCredit(movieId) }
    }

    override suspend fun getMovieRecommendation(movieId: Int, page: Int): PagingResult<Movie> {
        return getHttpRequestResult { movieService.getMovieRecommendation(movieId, page) }
    }

    override suspend fun getMovieUserReview(movieId: Int): PagingResult<MovieUserReview> {
        return getHttpRequestResult { movieService.getMovieUserReview(movieId) }
    }

    override suspend fun getDiscoveredMovieBasedGenre(page: Int, genreId: Int): PagingResult<Movie> {
        return getHttpRequestResult { movieService.getDiscoveredMovieBasedGenre(page, genreId) }
    }

    override suspend fun searchMovie(page: Int, query: String): PagingResult<Movie> {
        return getHttpRequestResult { movieService.searchMovie(page, query) }
    }
}