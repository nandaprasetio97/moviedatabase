package com.nandaprasetio.moviedatabase.presentation.misc

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nandaprasetio.moviedatabase.core.viewmodel.MovieGenreViewModel
import com.nandaprasetio.moviedatabase.core.viewmodel.PagingDataViewModel

class RecyclerViewFragmentScrollRetainer {
    fun restoreScrollValue(pagingDataViewModel: PagingDataViewModel, recyclerView: RecyclerView, lifecycleOwner: LifecycleOwner) {
        /*pagingDataViewModel.let { viewModel ->
            (recyclerView.layoutManager as LinearLayoutManager).also {
                viewModel.triggerScrollValueRetainerLiveDataToObserving()
                viewModel.scrollValueRetainerLiveData.observe(lifecycleOwner) { value ->
                    recyclerView.post { it.scrollToPositionWithOffset(0, -value) }
                }
            }
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    viewModel.incrementScrollValue(dy)
                }
            })
        }*/
    }

    fun dispose(recyclerView: RecyclerView) {
        /*recyclerView.clearOnScrollListeners()*/
    }
}