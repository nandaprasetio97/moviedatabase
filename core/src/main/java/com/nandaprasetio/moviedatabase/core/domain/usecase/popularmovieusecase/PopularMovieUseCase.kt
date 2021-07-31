package com.nandaprasetio.moviedatabase.core.domain.usecase.popularmovieusecase

import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow

interface PopularMovieUseCase {
    fun getPopularMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>>
}