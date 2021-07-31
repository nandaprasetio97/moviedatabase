package com.nandaprasetio.moviedatabase.core.data.service

import com.nandaprasetio.moviedatabase.core.BuildConfig
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.GenreList
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetailCredit
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieUserReview
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage.MovieDetailImage
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailvideo.MovieDetailVideo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieGenreList(@Query("page") page: Int): GenreList

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularMovieList(@Query("page") page: Int): PagingResult<Movie>

    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}")
    suspend fun getTopRatedMovieList(@Query("page") page: Int): PagingResult<Movie>

    @GET("movie/upcoming?api_key=${BuildConfig.API_KEY}")
    suspend fun getUpcomingMovieList(@Query("page") page: Int): PagingResult<Movie>

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetail

    @GET("movie/{movie_id}/images?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieDetailImage(@Path("movie_id") movieId: Int): MovieDetailImage

    @GET("movie/{movie_id}/videos?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieDetailVideo(@Path("movie_id") movieId: Int): MovieDetailVideo

    @GET("movie/{movie_id}/credits?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieDetailCredit(@Path("movie_id") movieId: Int): MovieDetailCredit

    @GET("movie/{movie_id}/recommendations?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieRecommendation(@Path("movie_id") movieId: Int, @Query("page") page: Int): PagingResult<Movie>

    @GET("movie/{movie_id}/reviews?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieUserReview(@Path("movie_id") movieId: Int): PagingResult<MovieUserReview>

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun getDiscoveredMovieBasedGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ): PagingResult<Movie>

    @GET("search/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun searchMovie(@Query("page") page: Int, @Query("query") query: String): PagingResult<Movie>
}