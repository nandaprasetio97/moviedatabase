package com.nandaprasetio.moviedatabase.core.presentation.epoxyholder

import android.view.View
import android.widget.TextView
import com.nandaprasetio.moviedatabase.core.R

class SingleTextCardEpoxyHolder: KotlinEpoxyHolder() {
    val containerCardView by bind<View>(R.id.card_view_container)
    val titleTextView by bind<TextView>(R.id.text_view_title)
}