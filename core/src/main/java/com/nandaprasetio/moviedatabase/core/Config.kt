package com.nandaprasetio.moviedatabase.core

import androidx.paging.PagingConfig

object Config {
    val DEFAULT_PAGING_CONFIG = PagingConfig(
        pageSize = 20,
        prefetchDistance = 6,
        enablePlaceholders = true,
        initialLoadSize = 20
    )
}