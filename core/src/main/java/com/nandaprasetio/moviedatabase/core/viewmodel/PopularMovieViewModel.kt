package com.nandaprasetio.moviedatabase.core.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.nandaprasetio.moviedatabase.core.domain.usecase.popularmovieusecase.PopularMovieUseCase
import com.nandaprasetio.moviedatabase.core.misc.flow.FlowWrapper
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase
): PagingDataViewModel() {
    private val popularMoviePagingDataFlowWrapper: FlowWrapper<PagingData<BaseModelValue>> = FlowWrapper()

    fun getMoviePagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return popularMoviePagingDataFlowWrapper.assignFlow {
            popularMovieUseCase.getPopularMoviePagingDataFlow().cachedIn(viewModelScope)
        }.flow
    }
}