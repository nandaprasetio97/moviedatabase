package com.nandaprasetio.moviedatabase.core.pagingsource

import androidx.paging.PagingSource
import com.nandaprasetio.moviedatabase.core.CoroutineTestRule
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSourceImpl
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource.DiscoveredMovieBasedGenrePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepositoryImpl
import com.nandaprasetio.moviedatabase.core.di.module.NetworkModule
import com.nandaprasetio.moviedatabase.core.di.module.NetworkServiceModule
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.MovieItemModelValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DiscoveredMovieGenrePagingSourceUnitTest {
    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var discoveredMovieBasedGenrePagingSource: DiscoveredMovieBasedGenrePagingSource

    @Before
    fun before() {
        discoveredMovieBasedGenrePagingSource = DiscoveredMovieBasedGenrePagingSource(
            MovieRepositoryImpl(
                coroutineTestRule.testCoroutineDispatcher,
                MovieDataSourceImpl(
                    NetworkServiceModule().provideMovieService(
                        NetworkModule().provideRetrofit()
                    )
                ),
            ), 35
        )
    }

    @Test
    fun loadDiscoveredMovieGenreThroughPagingSource_isSuccessLoad() {
        runBlocking {
            var movieBasedGenrePagingSource = 0
            val loadResult: PagingSource.LoadResult<Int, BaseModelValue> = discoveredMovieBasedGenrePagingSource.load(
                PagingSource.LoadParams.Append(
                    1, 20, false
                )
            )
            Assert.assertTrue(loadResult is PagingSource.LoadResult.Page)
            (loadResult as PagingSource.LoadResult.Page).data.onEach { value ->
                if (value is MovieItemModelValue) {
                    movieBasedGenrePagingSource += 1
                }
            }
            Assert.assertTrue(movieBasedGenrePagingSource > 0)
        }
    }
}