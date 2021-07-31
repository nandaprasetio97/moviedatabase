package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.carouselcompoundmodelvalue

import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.BaseCompoundModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.BaseItemModelValue

abstract class BaseCarouselCompoundModelValue: BaseCompoundModelValue() {
    abstract val itemModelValueList: List<BaseItemModelValue>
}