package com.nandaprasetio.moviedatabase.core.ext

import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageitemmodelvalue.PosterImageItemModelValue

fun MutableList<BaseModelValue>.addAllMovie(moviePagingResult: PagingResult<Movie>) {
    moviePagingResult.also {
        this.addAll(it.results.map { movie ->
            PosterImageItemModelValue(id = "poster_image_movie_${movie.id}", image = null, tag = movie)
        })
    }
}