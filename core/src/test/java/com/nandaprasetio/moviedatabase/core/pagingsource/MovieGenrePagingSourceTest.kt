package com.nandaprasetio.moviedatabase.core.pagingsource

import androidx.paging.PagingSource
import com.nandaprasetio.moviedatabase.core.CoroutineTestRule
import com.nandaprasetio.moviedatabase.core.MockHelper
import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.MovieGenrePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MovieGenrePagingSourceTest {
    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var movieDataSource: MovieDataSource
    private lateinit var localMovieDataSource: LocalMovieDataSource
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieGenrePagingSource: MovieGenrePagingSource

    @Before
    fun before() {
        movieDataSource = org.mockito.kotlin.mock()
        localMovieDataSource = org.mockito.kotlin.mock()
        movieRepository = MovieRepositoryImpl(coroutineTestRule.testCoroutineDispatcher, movieDataSource, localMovieDataSource)
        movieGenrePagingSource = MovieGenrePagingSource(movieRepository)
    }

    @Test
    fun loadPagingSource_isSuccessLoad() {
        runBlockingTest {
            val genreList = MockHelper.getGenreList()
            whenever(movieDataSource.getMovieGenreList(1)).thenReturn(genreList)

            val loadResult = movieGenrePagingSource.load(
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
            whenever(movieDataSource.getMovieGenreList(1)).thenThrow(exception)

            val loadResult = movieGenrePagingSource.load(
                PagingSource.LoadParams.Refresh(1, 20, false)
            )
            Assert.assertTrue(loadResult is PagingSource.LoadResult.Error)
            Assert.assertEquals((loadResult as PagingSource.LoadResult.Error).throwable, exception)
        }
    }
}