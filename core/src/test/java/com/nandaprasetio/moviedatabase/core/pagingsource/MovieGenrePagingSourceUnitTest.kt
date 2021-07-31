package com.nandaprasetio.moviedatabase.core.pagingsource

import androidx.paging.PagingSource
import com.nandaprasetio.moviedatabase.core.CoroutineTestRule
import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSourceImpl
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.MovieGenrePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepositoryImpl
import com.nandaprasetio.moviedatabase.core.di.module.NetworkModule
import com.nandaprasetio.moviedatabase.core.di.module.NetworkServiceModule
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class MovieGenrePagingSourceUnitTest {
    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var movieGenrePagingSource: MovieGenrePagingSource

    @Before
    fun before() {
        movieGenrePagingSource = MovieGenrePagingSource(
            MovieRepositoryImpl(
                coroutineTestRule.testCoroutineDispatcher,
                MovieDataSourceImpl(
                    NetworkServiceModule().provideMovieService(
                        NetworkModule().provideRetrofit()
                    )
                ),
                mock(LocalMovieDataSource::class.java)
            )
        )
    }

    @Test
    fun loadMovieGenreThroughPagingSource_isSuccessLoad() {
        runBlocking {
            var movieGenreCount = 0
            val loadResult: PagingSource.LoadResult<Int, BaseModelValue> = movieGenrePagingSource.load(
                PagingSource.LoadParams.Append(
                    1, 20, false
                )
            )
            Assert.assertTrue(loadResult is PagingSource.LoadResult.Page)
            (loadResult as PagingSource.LoadResult.Page).data.onEach { value ->
                if (value is GenreItemModelValue) {
                    movieGenreCount += 1
                }
            }
            Assert.assertTrue(movieGenreCount > 0)
        }
    }
}