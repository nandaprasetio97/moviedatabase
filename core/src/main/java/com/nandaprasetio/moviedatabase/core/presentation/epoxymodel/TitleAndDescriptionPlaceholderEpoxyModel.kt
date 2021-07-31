package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.TitleAndDescriptionEpoxyHolder

@EpoxyModelClass
abstract class TitleAndDescriptionPlaceholderEpoxyModel: EpoxyModelWithHolder<TitleAndDescriptionEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    override fun getDefaultLayout(): Int {
        return R.layout.item_title_and_description_placeholder
    }
}