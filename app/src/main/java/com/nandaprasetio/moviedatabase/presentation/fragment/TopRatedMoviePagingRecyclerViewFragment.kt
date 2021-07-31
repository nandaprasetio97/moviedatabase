package com.nandaprasetio.moviedatabase.presentation.fragment

import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.presentation.epoxycontroller.BasePagingDataEpoxyController
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.viewmodel.PagingDataViewModel
import com.nandaprasetio.moviedatabase.core.viewmodel.TopRatedMovieViewModel
import com.nandaprasetio.moviedatabase.presentation.epoxycontroller.TopRatedMoviePagingDataEpoxyController
import com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration.PagingRecyclerViewFragmentConfiguration
import com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration.copy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ObsoleteCoroutinesApi
@AndroidEntryPoint
class TopRatedMoviePagingRecyclerViewFragment: BasePagingRecyclerViewFragment<BaseModelValue>() {
    private val topRatedMovieViewModel: TopRatedMovieViewModel by viewModels()

    override fun getPagingDataEpoxyController(): BasePagingDataEpoxyController<BaseModelValue> {
        return TopRatedMoviePagingDataEpoxyController()
    }

    override fun getPagingDataViewModel(): PagingDataViewModel {
        return topRatedMovieViewModel
    }

    override fun getPagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return topRatedMovieViewModel.getTopRatedMoviePagingDataFlow()
    }

    override fun getPagingRecyclerViewFragmentConfiguration(): PagingRecyclerViewFragmentConfiguration {
        return super.getPagingRecyclerViewFragmentConfiguration().copy(spanCount = 2)
    }
}