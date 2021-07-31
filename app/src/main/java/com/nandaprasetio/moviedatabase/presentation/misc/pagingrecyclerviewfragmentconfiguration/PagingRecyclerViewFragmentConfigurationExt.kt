package com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration

import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
fun PagingRecyclerViewFragmentConfiguration.copy(
    pagingRecyclerViewFragmentValue: PagingRecyclerViewFragmentValue
): PagingRecyclerViewFragmentConfiguration {
    return PagingRecyclerViewFragmentConfiguration(
        pagingRecyclerViewFragmentValue,
        this.pagingRecyclerViewFragmentLayoutManagerConfiguration,
        this.errorProvider
    )
}

@ObsoleteCoroutinesApi
fun PagingRecyclerViewFragmentConfiguration.copy(
    spanCount: Int = this.pagingRecyclerViewFragmentValue.spanCount,
    itemSpacingDp: Int = this.pagingRecyclerViewFragmentValue.itemSpacingDp,
    startLoadFirst: Boolean = this.pagingRecyclerViewFragmentValue.startLoadFirst
): PagingRecyclerViewFragmentConfiguration {
    return PagingRecyclerViewFragmentConfiguration(
        this.pagingRecyclerViewFragmentValue.copy(spanCount = spanCount, itemSpacingDp = itemSpacingDp, startLoadFirst = startLoadFirst),
        this.pagingRecyclerViewFragmentLayoutManagerConfiguration,
        this.errorProvider
    )
}