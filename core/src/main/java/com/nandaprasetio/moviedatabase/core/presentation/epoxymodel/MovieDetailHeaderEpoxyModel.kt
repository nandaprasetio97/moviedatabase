package com.nandaprasetio.moviedatabase.core.presentation.epoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.moviedatabase.core.R
import com.nandaprasetio.moviedatabase.core.ext.setImageWithGlide
import com.nandaprasetio.moviedatabase.core.presentation.epoxyholder.MovieDetailHeaderEpoxyHolder
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.MovieDetailHeaderModelValue
import kotlin.math.round

@EpoxyModelClass
abstract class MovieDetailHeaderEpoxyModel: EpoxyModelWithHolder<MovieDetailHeaderEpoxyHolder>() {
    @EpoxyAttribute
    var movieDetailHeaderModelValue: MovieDetailHeaderModelValue? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_movie_detail_header
    }

    override fun bind(holder: MovieDetailHeaderEpoxyHolder) {
        super.bind(holder)
        movieDetailHeaderModelValue?.movieDetail?.also {
            val voteCountText = "(${it.voteCount} votes)"
            holder.backdropImageView.setImageWithGlide(
                it.backdropPath?.toStringBasedSizeExpression("w300")
            )
            holder.posterImageView.setImageWithGlide(it.posterPath?.toString())
            holder.titleTextView.text = it.title
            holder.userScoreTextView.text = (round((5.0 * it.voteAverage / 10.0) * 100) / 100).toString()
            holder.voteCountTextView.text = voteCountText
            holder.productionCompanyTextView.text = if (it.productionCompanies.isNotEmpty()) {
                it.productionCompanies[0].name
            } else {
                "Not found"
            }
            holder.releaseDateTextView.text = it.releaseDate
        }
    }
}