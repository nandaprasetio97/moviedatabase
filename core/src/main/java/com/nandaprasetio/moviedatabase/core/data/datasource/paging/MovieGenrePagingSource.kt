package com.nandaprasetio.moviedatabase.core.data.datasource.paging

import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.*
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.SeparatorItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionItemModelValue

class MovieGenrePagingSource(
    private val movieRepository: MovieRepository
): BasedIntKeyedPagingSource<BaseModelValue>() {
    @Suppress("UNCHECKED_CAST")
    override suspend fun getPagingResult(page: Int): LoadDataResult<PagingResult<BaseModelValue>> {
        return movieRepository.getMovieGenreList(page).let {
            when (it) {
                is LoadDataResult.Success -> {
                    val newResult: MutableList<BaseModelValue> = mutableListOf()
                    if (page == 1) {
                        newResult.add(TitleAndDescriptionItemModelValue("title_and_description_movie", null, null))
                        newResult.add(SeparatorItemModelValue("separator_movie"))
                    }
                    newResult.addAll(it.value.genres.map { genre -> GenreItemModelValue(genre) })
                    LoadDataResult.Success(PagingResult(1, newResult, 0, it.value.genres.size))
                }
                is LoadDataResult.Failed -> LoadDataResult.Failed(it.t)
            }
        }
    }
}