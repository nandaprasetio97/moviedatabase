package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.TitleAndDescriptionEpoxyHolder

@EpoxyModelClass
abstract class TitleAndDescriptionEpoxyModel: EpoxyModelWithHolder<TitleAndDescriptionEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    var description: String? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_title_and_description
    }

    override fun bind(view: TitleAndDescriptionEpoxyHolder) {
        super.bind(view)
        view.titleTextView.visibility = if (title.isNullOrBlank()) View.GONE else View.VISIBLE
        view.titleTextView.text = title
        view.descriptionTextView.visibility = if (description.isNullOrBlank()) View.GONE else View.VISIBLE
        view.descriptionTextView.text = description
    }
}