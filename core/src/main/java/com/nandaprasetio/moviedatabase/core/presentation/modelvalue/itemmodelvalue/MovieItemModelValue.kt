package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue

import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie

data class MovieItemModelValue(
    val movie: Movie,
    override val tag: Any? = null
): BaseItemModelValue()