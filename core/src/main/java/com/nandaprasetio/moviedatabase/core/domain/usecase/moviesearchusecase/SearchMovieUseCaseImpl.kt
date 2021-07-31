package com.nandaprasetio.moviedatabase.core.domain.usecase.moviesearchusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.Config
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.MovieSearchPagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): SearchMovieUseCase {
    override fun searchMoviePagingDataFlow(
        search: String
    ): Flow<PagingData<BaseModelValue>> {
        return Pager(
            config = Config.DEFAULT_PAGING_CONFIG,
            pagingSourceFactory = { MovieSearchPagingSource(movieRepository, search) }
        ).flow
    }
}