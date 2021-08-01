package com.nandaprasetio.moviedatabase.core.pagingsource.moviepagingsource

import androidx.paging.PagingSource
import com.nandaprasetio.moviedatabase.core.CoroutineTestRule
import com.nandaprasetio.moviedatabase.core.MockHelper
import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource.UpcomingMoviePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UpcomingPagingSourceTest {
    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var movieDataSource: MovieDataSource
    private lateinit var localMovieDataSource: LocalMovieDataSource
    private lateinit var movieRepository: MovieRepository
    private lateinit var upcomingMoviePagingSource: UpcomingMoviePagingSource

    @Before
    fun before() {
        movieDataSource = mock()
        localMovieDataSource = mock()
        movieRepository = MovieRepositoryImpl(coroutineTestRule.testCoroutineDispatcher, movieDataSource, localMovieDataSource)
        upcomingMoviePagingSource = UpcomingMoviePagingSource(movieRepository)
    }

    @Test
    fun loadPagingSource_isSuccessLoad() {
        runBlockingTest {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            whenever(movieDataSource.getUpcomingMovieList(1)).thenReturn(moviePagingResult)
            whenever(localMovieDataSource.deleteUpcomingMovieListToDatabase(1)).thenReturn(1)
            whenever(localMovieDataSource.addUpcomingMovieListToDatabase(1, moviePagingResult)).thenReturn(moviePagingResult)

            val loadResult = upcomingMoviePagingSource.load(
                PagingSource.LoadParams.Refresh(1, 20, false)
            )
            Assert.assertTrue(loadResult is PagingSource.LoadResult.Page)
            Assert.assertTrue((loadResult as PagingSource.LoadResult.Page).data.isNotEmpty())
        }
    }

    @Test
    fun loadPagingSource_isAlthoughRemoteFailedLoad() {
        runBlockingTest {
            val moviePagingResult = MockHelper.getMoviePagingResult()
            val exception = IllegalStateException()
            whenever(movieDataSource.getUpcomingMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getUpcomingMovieList(1)).thenReturn(moviePagingResult)

            val loadResult = upcomingMoviePagingSource.load(
                PagingSource.LoadParams.Refresh(1, 20, false)
            )
            Assert.assertTrue(loadResult is PagingSource.LoadResult.Page)
            Assert.assertTrue((loadResult as PagingSource.LoadResult.Page).data.isNotEmpty())
        }
    }

    @Test
    fun loadPagingSource_isFailedLoad() {
        runBlockingTest {
            val exception = IllegalStateException()
            whenever(movieDataSource.getUpcomingMovieList(1)).thenThrow(exception)
            whenever(localMovieDataSource.getUpcomingMovieList(1)).thenThrow(exception)

            val loadResult = upcomingMoviePagingSource.load(
                PagingSource.LoadParams.Refresh(1, 20, false)
            )
            Assert.assertTrue(loadResult is PagingSource.LoadResult.Error)
            Assert.assertEquals((loadResult as PagingSource.LoadResult.Error).throwable, exception)
        }
    }
}