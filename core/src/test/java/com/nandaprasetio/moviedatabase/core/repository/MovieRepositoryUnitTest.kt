package com.nandaprasetio.moviedatabase.core.repository

import com.nandaprasetio.moviedatabase.core.CoroutineTestRule
import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSourceImpl
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepositoryImpl
import com.nandaprasetio.moviedatabase.core.di.module.NetworkModule
import com.nandaprasetio.moviedatabase.core.di.module.NetworkServiceModule
import com.nandaprasetio.moviedatabase.core.getValueFromSuccessLoadResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class MovieRepositoryUnitTest {
    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @Before
    fun before() {
        movieRepositoryImpl = MovieRepositoryImpl(
            coroutineTestRule.testCoroutineDispatcher,
            MovieDataSourceImpl(
                NetworkServiceModule().provideMovieService(
                    NetworkModule().provideRetrofit()
                )
            ),
            mock(LocalMovieDataSource::class.java)
        )
    }

    @Test
    fun getMovieGenreList_hasData() {
        runBlocking {
            val genres = getValueFromSuccessLoadResult(movieRepositoryImpl.getMovieGenreList(1)).genres
            Assert.assertTrue(genres.isNotEmpty())
            genres.forEach { genre -> println(genre.name) }
        }
    }

    @Test
    fun getPopularMovieList_hasData() {
        runBlocking {
            val movies = getValueFromSuccessLoadResult(movieRepositoryImpl.getPopularMovieList(1)).results
            Assert.assertTrue(movies.isNotEmpty())
            movies.forEach { movie -> println(movie.title) }
        }
    }

    @Test
    fun getTopRatedMovieList_hasData() {
        runBlocking {
            val movies = getValueFromSuccessLoadResult(movieRepositoryImpl.getTopRatedMovieList(1)).results
            Assert.assertTrue(movies.isNotEmpty())
            movies.forEach { movie -> println(movie.title) }
        }
    }

    @Test
    fun getUpcomingMovieList_hasData() {
        runBlocking {
            val movies = getValueFromSuccessLoadResult(movieRepositoryImpl.getUpcomingMovieList(1)).results
            Assert.assertTrue(movies.isNotEmpty())
            movies.forEach { movie -> println(movie.title) }
        }
    }

    @Test
    fun getMovieDetail_hasData() {
        runBlocking {
            val movieDetail = getValueFromSuccessLoadResult(movieRepositoryImpl.getMovieDetail(744275))
            Assert.assertTrue(movieDetail.title.isNotBlank())
            println(movieDetail.title); println(movieDetail.status)
        }
    }

    @Test
    fun getMovieDetailImage_hasData() {
        runBlocking {
            val movieDetailImage = getValueFromSuccessLoadResult(movieRepositoryImpl.getMovieDetailImage(3))
            println(movieDetailImage.posters)
            println(movieDetailImage.backdrops)
            Assert.assertTrue(movieDetailImage.posters.isNotEmpty() && movieDetailImage.backdrops.isNotEmpty())
        }
    }

    @Test
    fun getMovieDetailVideo_hasData() {
        runBlocking {
            val movieDetailVideo = getValueFromSuccessLoadResult(movieRepositoryImpl.getMovieDetailVideo(744275))
            Assert.assertTrue(movieDetailVideo.results.isNotEmpty())
            println(movieDetailVideo.results)
        }
    }

    @Test
    fun getMovieDetailCast_hasData() {
        runBlocking {
            val movieDetailVideo = getValueFromSuccessLoadResult(movieRepositoryImpl.getMovieDetailCredit(744275))
            Assert.assertTrue(movieDetailVideo.cast.isNotEmpty())
            println(movieDetailVideo.cast)
        }
    }

    @Test
    fun getMovieUserReview_hasData() {
        runBlocking {
            val movieUserReviews = getValueFromSuccessLoadResult(movieRepositoryImpl.getMovieUserReview(337404)).results
            movieUserReviews.forEach { movieUserReview -> println(movieUserReview.author); println(movieUserReview.content); }
        }
    }

    @Test
    fun getMovieRecommendation_hasData() {
        runBlocking {
            val movieRecommendation = getValueFromSuccessLoadResult(movieRepositoryImpl.getMovieRecommendation(337404, 1)).results
            movieRecommendation.forEach { movieUserReview -> println(movieUserReview.title) }
        }
    }

    @Test
    fun getDiscoveredMovieBasedGenre_hasData() {
        runBlocking {
            val movieUserReviews = getValueFromSuccessLoadResult(movieRepositoryImpl.getDiscoveredMovieBasedGenre(1, 35)).results
            Assert.assertTrue(movieUserReviews.isNotEmpty())
            movieUserReviews.forEach { movie ->
                println(movie.title)
                movie.genreIds.forEach { genreId ->
                    println("- $genreId")
                }
            }
        }
    }

    @Test
    fun searchMovie_hasData() {
        runBlocking {
            val movieSearchResult = getValueFromSuccessLoadResult(movieRepositoryImpl.searchMovie(1, "disney")).results
            Assert.assertTrue(movieSearchResult.isNotEmpty())
            movieSearchResult.forEach { movie ->
                println(movie.title)
                movie.genreIds.forEach { genreId ->
                    println("- $genreId")
                }
            }
        }
    }
}