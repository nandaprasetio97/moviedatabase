package com.nandaprasetio.moviedatabase.core.domain.usecase.moviedetailusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.Config
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.MovieDetailPagingSource
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.MovieGenrePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): MovieDetailUseCase {
    override fun getMovieDetailPagingDataFlow(movieId: Int): Flow<PagingData<BaseModelValue>> {
        return Pager(
            config = Config.DEFAULT_PAGING_CONFIG,
            pagingSourceFactory = { MovieDetailPagingSource(movieRepository, movieId) }
        ).flow
    }
}