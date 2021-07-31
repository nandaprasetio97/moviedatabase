package com.nandaprasetio.moviedatabase.core.presentation.epoxyholder

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.nandaprasetio.moviedatabase.core.R

class ErrorEpoxyHolder: KotlinEpoxyHolder() {
    val titleTextView by bind<TextView>(R.id.text_view_title)
    val descriptionTextView by bind<TextView>(R.id.text_view_description)
    val iconImageView by bind<ImageView>(R.id.image_view_content)
    val retryButton by bind<Button>(R.id.button_retry)
}