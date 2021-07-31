package com.nandaprasetio.moviedatabase.core.domain.usecase.upcomingmovieusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.Config
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource.UpcomingMoviePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): UpcomingMovieUseCase {
    override fun getUpcomingMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return Pager(
            config = Config.DEFAULT_PAGING_CONFIG,
            pagingSourceFactory = { UpcomingMoviePagingSource(movieRepository) }
        ).flow
    }
}