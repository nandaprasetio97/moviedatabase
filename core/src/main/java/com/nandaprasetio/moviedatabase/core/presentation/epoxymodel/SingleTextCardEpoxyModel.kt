package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel

import android.content.Context
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.SingleTextCardEpoxyHolder

@EpoxyModelClass
abstract class SingleTextCardEpoxyModel: EpoxyModelWithHolder<SingleTextCardEpoxyHolder>(), HasLeftRightPaddingEpoxyModel {
    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    var onClickListener: ((Context) -> Unit)? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_card_single_text
    }

    override fun bind(view: SingleTextCardEpoxyHolder) {
        super.bind(view)
        view.containerCardView.setOnClickListener { onClickListener?.invoke(it.context) }
        view.titleTextView.text = title
    }
}