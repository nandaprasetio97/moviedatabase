package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.posterimageepoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.posterimageepoxyholder.CarouselPosterImageEpoxyHolder
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.HasLeftRightPaddingEpoxyModel
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageitemmodelvalue.CarouselPosterImageItemModelValue

@EpoxyModelClass
abstract class CarouselPosterImageEpoxyModel: EpoxyModelWithHolder<CarouselPosterImageEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    private val posterImageEpoxyModelBinder: PosterImageEpoxyModelBinder = PosterImageEpoxyModelBinder()

    @EpoxyAttribute
    var carouselPosterImageItemModelValue: CarouselPosterImageItemModelValue? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_poster_image_carousel
    }

    override fun bind(view: CarouselPosterImageEpoxyHolder) {
        super.bind(view)
        posterImageEpoxyModelBinder.bind(view, carouselPosterImageItemModelValue)
    }
}