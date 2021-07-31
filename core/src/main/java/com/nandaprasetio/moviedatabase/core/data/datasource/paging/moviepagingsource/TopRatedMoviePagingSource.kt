package com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource

import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.ext.addAllMovie
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageitemmodelvalue.PosterImageItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.SeparatorItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionItemModelValue

class TopRatedMoviePagingSource(private val movieRepository: MovieRepository): BaseMoviePagingSource() {
    override suspend fun getPagingResult(page: Int): LoadDataResult<PagingResult<BaseModelValue>> {
        return movieRepository.getTopRatedMovieList(page).let {
            when (it) {
                is LoadDataResult.Success -> {
                    val newResult: MutableList<BaseModelValue> = mutableListOf()
                    if (page == 1) {
                        newResult.add(TitleAndDescriptionItemModelValue("title_and_description_top_rated_movie", null, null))
                        newResult.add(SeparatorItemModelValue("separator_popular_movie"))
                    }
                    newResult.addAllMovie(it.value)
                    LoadDataResult.Success(PagingResult(it.value.page, newResult, it.value.totalPages, it.value.totalResults))
                }
                is LoadDataResult.Failed -> LoadDataResult.Failed(it.t)
            }
        }
    }
}