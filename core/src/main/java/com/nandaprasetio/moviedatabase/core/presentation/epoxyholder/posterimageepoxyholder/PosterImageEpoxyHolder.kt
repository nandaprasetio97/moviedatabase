package com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.posterimageepoxyholder

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.KotlinEpoxyHolder

open class PosterImageEpoxyHolder: KotlinEpoxyHolder() {
    val containerCardView by bind<View>(R.id.card_view_container)
    val contentConstraintLayout by bind<ConstraintLayout>(R.id.constraint_layout_content)
    val contentImageView by bind<ImageView>(R.id.image_view_content)
}