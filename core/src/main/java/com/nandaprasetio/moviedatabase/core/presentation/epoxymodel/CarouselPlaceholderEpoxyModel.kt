package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.CarouselPlaceholderEpoxyHolder

@EpoxyModelClass
abstract class CarouselPlaceholderEpoxyModel: EpoxyModelWithHolder<CarouselPlaceholderEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    override fun getDefaultLayout(): Int {
        return R.layout.item_carousel_placeholder
    }
}