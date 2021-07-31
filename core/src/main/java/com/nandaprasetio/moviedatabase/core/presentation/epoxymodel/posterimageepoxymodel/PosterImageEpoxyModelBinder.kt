package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.posterimageepoxymodel

import android.util.TypedValue
import androidx.constraintlayout.widget.ConstraintLayout
import com.nandaprasetio.moviedatabase.core.domain.entity.Dimension
import com.nandaprasetio.moviedatabase.core.ext.setImageWithGlide
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.posterimageepoxyholder.PosterImageEpoxyHolder
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageitemmodelvalue.BasePosterImageItemModelValue

class PosterImageEpoxyModelBinder {
    fun bind(view: PosterImageEpoxyHolder, basePosterImageItemModelValue: BasePosterImageItemModelValue?) {
        basePosterImageItemModelValue?.also { value ->
            value.dimension?.also {
                if (view.contentImageView.layoutParams is ConstraintLayout.LayoutParams) {
                    val displayMetrics = view.containerCardView.context.resources.displayMetrics
                    val layoutParams = view.contentImageView.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.width = 0
                    layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150.0f, displayMetrics).toInt()
                    layoutParams.dimensionRatio = "${it.width}:${it.height}"
                }
            }
            view.containerCardView.setOnClickListener { value.onClickListener?.invoke(it.context) }
            view.contentImageView.setImageWithGlide(value.image)
        }
    }
}