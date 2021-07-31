package com.nandaprasetio.moviedatabase.core.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult

abstract class BasedIntKeyedPagingSource<T: Any>: PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 1
        val nextPage = page + 1
        return getPagingResult(page).let {
            when (it) {
                is LoadDataResult.Success -> {
                    val nextPageFromDataResult = it.value.page + 1
                    LoadResult.Page(
                        data = it.value.results,
                        prevKey = null,
                        nextKey = if (nextPageFromDataResult <= it.value.totalPages) nextPage else null
                    )
                }
                is LoadDataResult.Failed -> {
                    LoadResult.Error(it.t)
                }
            }
        }
    }

    abstract suspend fun getPagingResult(page: Int): LoadDataResult<PagingResult<T>>
}