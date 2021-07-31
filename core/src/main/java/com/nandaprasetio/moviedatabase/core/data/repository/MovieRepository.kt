package com.nandaprasetio.moviedatabase.core.data.repository

import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.GenreList
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetailCredit
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieUserReview
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage.MovieDetailImage
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailvideo.MovieDetailVideo
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult

interface MovieRepository {
    suspend fun getMovieGenreList(page: Int): LoadDataResult<GenreList>
    suspend fun getPopularMovieList(page: Int): LoadDataResult<PagingResult<Movie>>
    suspend fun getTopRatedMovieList(page: Int): LoadDataResult<PagingResult<Movie>>
    suspend fun getUpcomingMovieList(page: Int): LoadDataResult<PagingResult<Movie>>
    suspend fun getMovieDetail(movieId: Int): LoadDataResult<MovieDetail>
    suspend fun getMovieDetailImage(movieId: Int): LoadDataResult<MovieDetailImage>
    suspend fun getMovieDetailVideo(movieId: Int): LoadDataResult<MovieDetailVideo>
    suspend fun getMovieDetailCredit(movieId: Int): LoadDataResult<MovieDetailCredit>
    suspend fun getMovieRecommendation(movieId: Int, page: Int): LoadDataResult<PagingResult<Movie>>
    suspend fun getMovieUserReview(movieId: Int): LoadDataResult<PagingResult<MovieUserReview>>
    suspend fun getDiscoveredMovieBasedGenre(page: Int, genreId: Int): LoadDataResult<PagingResult<Movie>>
    suspend fun searchMovie(page: Int, query: String): LoadDataResult<PagingResult<Movie>>
}