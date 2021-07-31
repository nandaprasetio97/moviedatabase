package com.nandaprasetio.moviedatabase.core.domain.usecase.discoveredmoviebasedgenreusecase

import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import kotlinx.coroutines.flow.Flow

interface DiscoveredMovieBasedGenreUseCase {
    fun getDiscoveredMovieBasedGenrePagingDataFlow(genreId: Int): Flow<PagingData<BaseModelValue>>
}