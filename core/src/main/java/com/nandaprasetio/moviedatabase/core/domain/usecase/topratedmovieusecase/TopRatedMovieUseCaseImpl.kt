package com.nandaprasetio.moviedatabase.core.domain.usecase.topratedmovieusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.Config
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource.TopRatedMoviePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): TopRatedMovieUseCase {
    override fun getTopRatedMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return Pager(
            config = Config.DEFAULT_PAGING_CONFIG,
            pagingSourceFactory = { TopRatedMoviePagingSource(movieRepository) }
        ).flow
    }
}