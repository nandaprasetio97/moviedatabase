package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue

import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail

data class MovieDetailHeaderModelValue(
    override val id: String,
    val movieDetail: MovieDetail,
    override val tag: Any? = null
): BaseItemModelValue()