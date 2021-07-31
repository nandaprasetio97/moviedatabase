package com.nandaprasetio.moviedatabase.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.presentation.BaseFragment
import com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration.PagingRecyclerViewFragmentConfiguration
import com.nandaprasetio.moviedatabase.core.presentation.epoxycontroller.BasePagingDataEpoxyController
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.viewmodel.PagingDataViewModel
import com.nandaprasetio.moviedatabase.core.viewmodel.ParallelLoadingViewModel
import com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration.GridPagingRecyclerViewFragmentLayoutManagerConfiguration
import com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration.PagingRecyclerViewFragmentValue
import com.nandaprasetio.moviedatabase.databinding.FragmentRecyclerViewPagingBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ObsoleteCoroutinesApi
abstract class BasePagingRecyclerViewFragment<PagingDataType: BaseModelValue>: BaseFragment<FragmentRecyclerViewPagingBinding>() {
    private lateinit var _basePagingDataEpoxyController: BasePagingDataEpoxyController<PagingDataType>
    protected val basePagingDataEpoxyController: BasePagingDataEpoxyController<PagingDataType>
        get() {
            return _basePagingDataEpoxyController
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val configuration: PagingRecyclerViewFragmentConfiguration = getPagingRecyclerViewFragmentConfiguration()
        viewBinding?.also { binding ->
            _basePagingDataEpoxyController = getPagingDataEpoxyController()
            _basePagingDataEpoxyController.addLoadStateListener {
                onLoadStateListener(it, binding, configuration, view)
            }
            binding.recyclerViewContent.also {
                it.layoutManager = configuration.pagingRecyclerViewFragmentLayoutManagerConfiguration.getPagingRecyclerViewLayoutManager(
                    this.requireContext(), configuration.pagingRecyclerViewFragmentValue, _basePagingDataEpoxyController
                )
                it.itemAnimator = null
                it.setItemSpacingDp(configuration.pagingRecyclerViewFragmentValue.itemSpacingDp)
                it.setController(_basePagingDataEpoxyController)
                if (configuration.pagingRecyclerViewFragmentValue.startLoadFirst) {
                    triggerCollectPagingData()
                }
            }
            binding.swipeRefreshLayoutContent.setOnRefreshListener { refresh() }
        }
    }

    private fun onLoadStateListener(
        combinedLoadStates: CombinedLoadStates,
        binding: FragmentRecyclerViewPagingBinding,
        configuration: PagingRecyclerViewFragmentConfiguration,
        view: View
    ) {
        val pagingDataViewModel = getPagingDataViewModel()
        combinedLoadStates.also {
            binding.progressBarContent.visibility = if (it.source.refresh is LoadState.Loading) View.VISIBLE else View.GONE
            _basePagingDataEpoxyController.loading = it.source.append is LoadState.Loading

            // Refresh
            val refresh = it.source.refresh
            val isError = refresh is LoadState.Error
            binding.progressBarContent.isVisible = (refresh is LoadState.Loading).let { result ->
                if (!isError) { if (pagingDataViewModel.refreshWithSwiping) { false } else { result } } else { result }
            }
            binding.swipeRefreshLayoutContent.isVisible = (refresh !is LoadState.Loading).let { result ->
                if (!isError) { if (pagingDataViewModel.refreshWithSwiping) { !isError } else { result } } else { !isError }
            }
            binding.layoutViewError.root.isVisible = refresh is LoadState.Error
            if (refresh is LoadState.Error) {
                val errorProviderResult = configuration.errorProvider.provideErrorResult(refresh.error, view.context)
                errorProviderResult.iconResId?.also { resId ->
                    binding.layoutViewError.imageViewError.setImageResource(resId)
                }
                binding.layoutViewError.textViewTitle.text = errorProviderResult.title
                binding.layoutViewError.textViewDescription.text = errorProviderResult.message
                binding.layoutViewError.buttonRetry.setOnClickListener { _basePagingDataEpoxyController.retry() }
            }
            binding.swipeRefreshLayoutContent.isRefreshing = refresh is LoadState.Loading && pagingDataViewModel.refreshWithSwiping

            // Append
            val append = it.source.append
            _basePagingDataEpoxyController.loading = append is LoadState.Loading
            _basePagingDataEpoxyController.error = if (append is LoadState.Error) {
                configuration.errorProvider.provideErrorResult(append.error, view.context)
            } else {
                null
            }

            if (pagingDataViewModel.refreshWithSwiping) {
                pagingDataViewModel.refreshWithSwiping = false
            }
        }
    }

    private fun refresh() {
        val pagingDataViewModel = getPagingDataViewModel()
        if (pagingDataViewModel is ParallelLoadingViewModel) {
            pagingDataViewModel.clearParallelLoadingResult()
        }
        pagingDataViewModel.refreshWithSwiping = true
        _basePagingDataEpoxyController.refresh()
    }

    protected fun triggerCollectPagingData() {
        lifecycleScope.launch {
            getPagingDataFlow().collect { pagingData ->
                _basePagingDataEpoxyController.submitData(pagingData)
            }
        }
    }

    protected abstract fun getPagingDataEpoxyController(): BasePagingDataEpoxyController<PagingDataType>

    protected abstract fun getPagingDataViewModel(): PagingDataViewModel

    protected abstract fun getPagingDataFlow(): Flow<PagingData<PagingDataType>>

    protected open fun getPagingRecyclerViewFragmentConfiguration(): PagingRecyclerViewFragmentConfiguration {
        return PagingRecyclerViewFragmentConfiguration(
            PagingRecyclerViewFragmentValue(3, 16),
            GridPagingRecyclerViewFragmentLayoutManagerConfiguration()
        )
    }

    override fun onGetViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecyclerViewPagingBinding {
        return FragmentRecyclerViewPagingBinding.inflate(inflater, container, false)
    }
}