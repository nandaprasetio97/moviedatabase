package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.LoadingEpoxyHolder

@EpoxyModelClass
abstract class LoadingEpoxyModel: EpoxyModelWithHolder<LoadingEpoxyHolder>() {
    override fun getDefaultLayout(): Int {
        return R.layout.item_loading
    }
}