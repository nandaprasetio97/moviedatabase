package com.nandaprasetio.moviedatabase.core.repository

import com.nandaprasetio.moviedatabase.core.CoroutineTestRule
import com.nandaprasetio.moviedatabase.core.MockHelper
import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepositoryImpl
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.GenreList
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetailCredit
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieUserReview
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MovieRepositoryUnitTest {
    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var movieDataSource: MovieDataSource
    private lateinit var localMovieDataSource: LocalMovieDataSource
    private lateinit var movieRepository: MovieRepository

    @Before
    fun before() {
        movieDataSource = mock()
        localMovieDataSource = mock()
        movieRepository = MovieRepositoryImpl(coroutineTestRule.testCoroutineDispatcher, movieDataSource, localMovieDataSource)
    }

    @Test
    fun getMovieGenreList_isSuccess() {
        runBlocking {
            val genreList = MockHelper.getGenreList()
            whenever(movieDataSource.getMovieGenreList(1)).thenReturn(genreList)

            val loadDataResult = movieRepository.getMovieGenreList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(genreList))
        }
    }

    @Test
    fun getMovieGenreList_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getMovieGenreList(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getMovieGenreList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<GenreList>(exception))
        }
    }

    @Test
    fun getPopularMovieList_isSuccess() {
        runBlocking {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            whenever(movieDataSource.getPopularMovieList(1)).thenReturn(moviePagingResult)
            whenever(localMovieDataSource.deletePopularMovieListToDatabase(1)).thenReturn(1)
            whenever(localMovieDataSource.addPopularMovieListToDatabase(1, moviePagingResult)).thenReturn(moviePagingResult)

            val loadDataResult = movieRepository.getPopularMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(moviePagingResult))
        }
    }

    @Test
    fun getPopularMovieList_isSuccessAlthoughRemoteFailed() {
        runBlocking {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            val exception = IllegalStateException()
            whenever(movieDataSource.getPopularMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getPopularMovieList(1)).thenReturn(moviePagingResult)

            val loadDataResult = movieRepository.getPopularMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(moviePagingResult))
        }
    }

    @Test
    fun getPopularMovieList_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getPopularMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getPopularMovieList(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getPopularMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<PagingResult<Movie>>(exception))
        }
    }

    @Test
    fun getTopRatedMovieList_isSuccess() {
        runBlocking {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            whenever(movieDataSource.getTopRatedMovieList(1)).thenReturn(moviePagingResult)
            whenever(localMovieDataSource.deleteTopRatedMovieListToDatabase(1)).thenReturn(1)
            whenever(localMovieDataSource.addTopRatedMovieListToDatabase(1, moviePagingResult)).thenReturn(moviePagingResult)

            val loadDataResult = movieRepository.getTopRatedMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(moviePagingResult))
        }
    }

    @Test
    fun getTopRatedMovieList_isSuccessAlthoughRemoteFailed() {
        runBlocking {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            val exception = IllegalStateException()
            whenever(movieDataSource.getTopRatedMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getTopRatedMovieList(1)).thenReturn(moviePagingResult)

            val loadDataResult = movieRepository.getTopRatedMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(moviePagingResult))
        }
    }

    @Test
    fun getTopRatedMovieList_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getTopRatedMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getTopRatedMovieList(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getTopRatedMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<PagingResult<Movie>>(exception))
        }
    }

    @Test
    fun getUpcomingMovieList_isSuccess() {
        runBlocking {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            whenever(movieDataSource.getUpcomingMovieList(1)).thenReturn(moviePagingResult)
            whenever(localMovieDataSource.deleteUpcomingMovieListToDatabase(1)).thenReturn(1)
            whenever(localMovieDataSource.addUpcomingMovieListToDatabase(1, moviePagingResult)).thenReturn(moviePagingResult)

            val loadDataResult = movieRepository.getUpcomingMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(moviePagingResult))
        }
    }

    @Test
    fun getUpcomingMovieList_isSuccessAlthoughRemoteFailed() {
        runBlocking {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            val exception = IllegalStateException()
            whenever(movieDataSource.getUpcomingMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getUpcomingMovieList(1)).thenReturn(moviePagingResult)

            val loadDataResult = movieRepository.getUpcomingMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(moviePagingResult))
        }
    }

    @Test
    fun getUpcomingMovieList_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getUpcomingMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getUpcomingMovieList(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getUpcomingMovieList(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<PagingResult<Movie>>(exception))
        }
    }

    @Test
    fun getMovieDetail_isSuccess() {
        runBlocking {
            val movieDetail = MockHelper.getMovieDetail()
            whenever(movieDataSource.getMovieDetail(1)).thenReturn(movieDetail)

            val loadDataResult = movieRepository.getMovieDetail(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(movieDetail))
        }
    }

    @Test
    fun getMovieDetail_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getMovieDetail(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getMovieDetail(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<MovieDetail>(exception))
        }
    }

    @Test
    fun getMovieDetailImage_isSuccess() {
        runBlocking {
            val movieDetailImage = MockHelper.getMovieDetailImage()
            whenever(movieDataSource.getMovieDetailImage(1)).thenReturn(movieDetailImage)

            val loadDataResult = movieRepository.getMovieDetailImage(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(movieDetailImage))
        }
    }

    @Test
    fun getMovieDetailImage_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getMovieDetailImage(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getMovieDetailImage(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<MovieDetail>(exception))
        }
    }

    @Test
    fun getMovieDetailVideo_isSuccess() {
        runBlocking {
            val movieDetailVideo = MockHelper.getMovieDetailVideo()
            whenever(movieDataSource.getMovieDetailVideo(1)).thenReturn(movieDetailVideo)

            val loadDataResult = movieRepository.getMovieDetailVideo(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(movieDetailVideo))
        }
    }

    @Test
    fun getMovieDetailVideo_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getMovieDetailVideo(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getMovieDetailVideo(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<MovieDetail>(exception))
        }
    }

    @Test
    fun getMovieDetailCredit_isSuccess() {
        runBlocking {
            val movieDetailCredit = MockHelper.getMovieDetailCredit()
            whenever(movieDataSource.getMovieDetailCredit(1)).thenReturn(movieDetailCredit)

            val loadDataResult = movieRepository.getMovieDetailCredit(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(movieDetailCredit))
        }
    }

    @Test
    fun getMovieDetailCredit_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getMovieDetailCredit(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getMovieDetailCredit(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<MovieDetailCredit>(exception))
        }
    }

    @Test
    fun getMovieUserReview_isSuccess() {
        runBlocking {
            val movieUserReview = MockHelper.getMovieUserReview()
            whenever(movieDataSource.getMovieUserReview(1)).thenReturn(movieUserReview)

            val loadDataResult = movieRepository.getMovieUserReview(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(movieUserReview))
        }
    }

    @Test
    fun getMovieUserReview_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getMovieUserReview(1)).thenThrow(exception)

            val loadDataResult = movieRepository.getMovieUserReview(1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<MovieUserReview>(exception))
        }
    }

    @Test
    fun getMovieRecommendation_isSuccess() {
        runBlocking {
            val movieRecommendation = MockHelper.getMoviePagingResult()
            whenever(movieDataSource.getMovieRecommendation(1, 1)).thenReturn(movieRecommendation)

            val loadDataResult = movieRepository.getMovieRecommendation(1, 1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(movieRecommendation))
        }
    }

    @Test
    fun getMovieRecommendation_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getMovieRecommendation(1, 1)).thenThrow(exception)

            val loadDataResult = movieRepository.getMovieRecommendation(1, 1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<PagingResult<Movie>>(exception))
        }
    }

    @Test
    fun getDiscoveredMovieBasedGenre_isSuccess() {
        runBlocking {
            val discoveredMovieBasedGenre = MockHelper.getMoviePagingResult()
            whenever(movieDataSource.getDiscoveredMovieBasedGenre(1, 1)).thenReturn(discoveredMovieBasedGenre)

            val loadDataResult = movieRepository.getDiscoveredMovieBasedGenre(1, 1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(discoveredMovieBasedGenre))
        }
    }

    @Test
    fun getDiscoveredMovieBasedGenre_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.getDiscoveredMovieBasedGenre(1, 1)).thenThrow(exception)

            val loadDataResult = movieRepository.getDiscoveredMovieBasedGenre(1, 1)
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<PagingResult<Movie>>(exception))
        }
    }

    @Test
    fun searchMovie_isSuccess() {
        runBlocking {
            val searchMovie = MockHelper.getMoviePagingResult()
            whenever(movieDataSource.searchMovie(1, "test")).thenReturn(searchMovie)

            val loadDataResult = movieRepository.searchMovie(1, "test")
            Assert.assertEquals(loadDataResult, LoadDataResult.Success(searchMovie))
        }
    }

    @Test
    fun searchMovie_isFailed() {
        runBlocking {
            val exception = IllegalStateException()
            whenever(movieDataSource.searchMovie(1, "test")).thenThrow(exception)

            val loadDataResult = movieRepository.searchMovie(1, "test")
            Assert.assertEquals(loadDataResult, LoadDataResult.Failed<PagingResult<Movie>>(exception))
        }
    }
}