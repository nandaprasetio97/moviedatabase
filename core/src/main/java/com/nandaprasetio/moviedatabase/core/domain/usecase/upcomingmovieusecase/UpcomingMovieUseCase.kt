package com.nandaprasetio.moviedatabase.core.domain.usecase.upcomingmovieusecase

import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow

interface UpcomingMovieUseCase {
    fun getUpcomingMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>>
}