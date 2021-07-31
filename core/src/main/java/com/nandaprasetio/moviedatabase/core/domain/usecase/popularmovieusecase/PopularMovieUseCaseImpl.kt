package com.nandaprasetio.moviedatabase.core.domain.usecase.popularmovieusecase

import androidx.paging.*
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource.PopularMoviePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.Config
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): PopularMovieUseCase {
    override fun getPopularMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return Pager(
            config = Config.DEFAULT_PAGING_CONFIG,
            pagingSourceFactory = { PopularMoviePagingSource(movieRepository) }
        ).flow
    }
}