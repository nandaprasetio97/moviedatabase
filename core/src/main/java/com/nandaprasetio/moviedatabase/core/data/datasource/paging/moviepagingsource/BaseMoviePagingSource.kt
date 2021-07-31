package com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource

import com.nandaprasetio.moviedatabase.core.data.datasource.paging.BasedIntKeyedPagingSource
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageitemmodelvalue.PosterImageItemModelValue

abstract class BaseMoviePagingSource: BasedIntKeyedPagingSource<BaseModelValue>()