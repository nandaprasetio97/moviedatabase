package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.carouselcompoundmodelvalue

import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.BaseItemModelValue

data class CarouselCompoundPlaceholderModelValue(
    override val id: String,
    override val tag: Any? = null
): BaseItemModelValue()