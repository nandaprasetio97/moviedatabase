package com.nandaprasetio.moviedatabase.core.domain.usecase.topratedmovieusecase

import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow

interface TopRatedMovieUseCase {
    fun getTopRatedMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>>
}