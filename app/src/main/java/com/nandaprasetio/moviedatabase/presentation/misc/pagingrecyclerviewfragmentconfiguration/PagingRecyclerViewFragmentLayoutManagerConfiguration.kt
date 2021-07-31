package com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nandaprasetio.moviedatabase.core.presentation.epoxycontroller.BasePagingDataEpoxyController
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
abstract class PagingRecyclerViewFragmentLayoutManagerConfiguration {
    abstract fun<PagingDataType: BaseModelValue> getPagingRecyclerViewLayoutManager(
        context: Context, pagingRecyclerViewFragmentValue: PagingRecyclerViewFragmentValue,
        pagingDataEpoxyController: BasePagingDataEpoxyController<PagingDataType>
    ): RecyclerView.LayoutManager
}