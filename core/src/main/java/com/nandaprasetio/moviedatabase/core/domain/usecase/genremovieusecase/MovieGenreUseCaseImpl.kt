package com.nandaprasetio.moviedatabase.core.domain.usecase.genremovieusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.Config
import com.nandaprasetio.moviedatabase.core.data.datasource.paging.MovieGenrePagingSource
import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieGenreUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): MovieGenreUseCase {
    override fun getMovieGenrePagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return Pager(
            config = Config.DEFAULT_PAGING_CONFIG,
            pagingSourceFactory = { MovieGenrePagingSource(movieRepository) },
        ).flow
    }
}