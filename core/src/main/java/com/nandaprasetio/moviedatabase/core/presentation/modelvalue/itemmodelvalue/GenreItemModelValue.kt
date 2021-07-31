package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue

import com.nandaprasetio.moviedatabase.core.domain.entity.genre.Genre

data class GenreItemModelValue(
    val genre: Genre,
    override val tag: Any? = null
): BaseItemModelValue()