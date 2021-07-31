package com.nandaprasetio.moviedatabase.core.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nandaprasetio.moviedatabase.core.domain.usecase.moviesearchusecase.SearchMovieUseCase
import com.nandaprasetio.moviedatabase.core.misc.flow.FlowWrapper
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
): ParallelLoadingViewModel() {
    private val searchMoviePagingDataFlowWrapper: FlowWrapper<PagingData<BaseModelValue>> = FlowWrapper()

    fun searchMoviePagingDataFlow(search: String): Flow<PagingData<BaseModelValue>> {
        return searchMoviePagingDataFlowWrapper.assignFlow {
            searchMovieUseCase.searchMoviePagingDataFlow(search).cachedIn(viewModelScope)
        }.flow
    }

    fun resetSearchMoviePagingDataFlow() {
        searchMoviePagingDataFlowWrapper.resetFlowToNull()
    }
}