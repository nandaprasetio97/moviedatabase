package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.posterimageandcontentepoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.posterimageandcontentepoxyholder.PosterImageAndContentEpoxyHolder
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.HasLeftRightPaddingEpoxyModel
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageandcontentitemmodelvalue.PosterImageAndContentItemModelValue

@EpoxyModelClass
abstract class PosterImageAndContentEpoxyModel: EpoxyModelWithHolder<PosterImageAndContentEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    private val posterImageAndContentEpoxyModelBinder: PosterImageAndContentEpoxyModelBinder = PosterImageAndContentEpoxyModelBinder()

    @EpoxyAttribute
    var posterImageAndContentItemModelValue: PosterImageAndContentItemModelValue? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_poster_image_and_content
    }

    override fun bind(view: PosterImageAndContentEpoxyHolder) {
        super.bind(view)
        posterImageAndContentEpoxyModelBinder.bind(view, posterImageAndContentItemModelValue)
    }
}