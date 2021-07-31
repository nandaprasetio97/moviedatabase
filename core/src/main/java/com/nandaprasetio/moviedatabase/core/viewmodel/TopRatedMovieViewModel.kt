package com.nandaprasetio.moviedatabase.core.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nandaprasetio.moviedatabase.core.domain.usecase.topratedmovieusecase.TopRatedMovieUseCase
import com.nandaprasetio.moviedatabase.core.misc.flow.FlowWrapper
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopRatedMovieViewModel @Inject constructor(
    private val topRatedMovieUseCase: TopRatedMovieUseCase
): PagingDataViewModel() {
    private val topRatedMoviePagingDataFlowWrapper: FlowWrapper<PagingData<BaseModelValue>> = FlowWrapper()

    fun getTopRatedMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return topRatedMoviePagingDataFlowWrapper.assignFlow {
            topRatedMovieUseCase.getTopRatedMoviePagingDataFlow().cachedIn(viewModelScope)
        }.flow
    }
}