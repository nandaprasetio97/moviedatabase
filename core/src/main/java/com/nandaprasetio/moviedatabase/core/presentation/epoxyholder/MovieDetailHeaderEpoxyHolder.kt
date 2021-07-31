package com.nandaprasetio.moviedatabase.core.presentation.epoxyholder

import android.widget.ImageView
import android.widget.TextView
import com.nandaprasetio.moviedatabase.core.R

class MovieDetailHeaderEpoxyHolder: KotlinEpoxyHolder() {
    val backdropImageView by bind<ImageView>(R.id.image_view_backdrop)
    val posterImageView by bind<ImageView>(R.id.image_view_poster)
    val titleTextView by bind<TextView>(R.id.text_view_title)
    val userScoreTextView by bind<TextView>(R.id.text_view_user_score)
    val voteCountTextView by bind<TextView>(R.id.text_view_vote_count)
    val productionCompanyTextView by bind<TextView>(R.id.text_view_production_company)
    val releaseDateTextView by bind<TextView>(R.id.text_view_release_date)
}