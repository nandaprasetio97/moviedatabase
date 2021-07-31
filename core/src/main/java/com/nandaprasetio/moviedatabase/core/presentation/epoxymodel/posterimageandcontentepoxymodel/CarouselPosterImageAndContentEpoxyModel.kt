package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.posterimageandcontentepoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.posterimageandcontentepoxyholder.CarouselPosterImageAndContentEpoxyHolder
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.HasLeftRightPaddingEpoxyModel
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageandcontentitemmodelvalue.CarouselPosterImageAndContentItemModelValue

@EpoxyModelClass
abstract class CarouselPosterImageAndContentEpoxyModel: EpoxyModelWithHolder<CarouselPosterImageAndContentEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    private val posterImageAndContentEpoxyModelBinder: PosterImageAndContentEpoxyModelBinder = PosterImageAndContentEpoxyModelBinder()

    @EpoxyAttribute
    var carouselPosterImageAndContentItemModelValue: CarouselPosterImageAndContentItemModelValue? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_poster_image_and_content_carousel
    }

    override fun bind(view: CarouselPosterImageAndContentEpoxyHolder) {
        super.bind(view)
        posterImageAndContentEpoxyModelBinder.bind(view, carouselPosterImageAndContentItemModelValue)
    }
}