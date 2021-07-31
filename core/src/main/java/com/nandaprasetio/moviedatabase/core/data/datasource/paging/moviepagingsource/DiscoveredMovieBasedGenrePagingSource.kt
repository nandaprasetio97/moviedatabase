package com.nandaprasetio.moviedatabase.core.data.datasource.paging.moviepagingsource

import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.ext.addAllMovie
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.*
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.ParallelLoadingCompoundModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.SeparatorItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionPlaceholderItemModelValue
import kotlinx.coroutines.delay

class DiscoveredMovieBasedGenrePagingSource(
    private val movieRepository: MovieRepository,
    private val genreId: Int,
): BaseMoviePagingSource() {
    @Suppress("UNCHECKED_CAST")
    override suspend fun getPagingResult(page: Int): LoadDataResult<PagingResult<BaseModelValue>> {
        return movieRepository.getDiscoveredMovieBasedGenre(page, genreId).let {
            when (it) {
                is LoadDataResult.Success -> {
                    val newResult: MutableList<BaseModelValue> = mutableListOf()
                    if (page == 1) {
                        newResult.add(
                            ParallelLoadingCompoundModelValue(
                                id = "parallel_loading_compound_title_and_description_genre",
                                oldItemModelValue = listOf(
                                    TitleAndDescriptionPlaceholderItemModelValue("title_and_description_placeholder_genre")
                                ),
                                onParallelLoading = {
                                    listOf(
                                        movieRepository.getMovieGenreList(1).let { result ->
                                            when (result) {
                                                is LoadDataResult.Success -> {
                                                    result.value.genres.find { genre -> genre.id == genreId.toString() }?.let { resultedGenre ->
                                                        TitleAndDescriptionItemModelValue(
                                                            id = "title_and_description_genre",
                                                            title = resultedGenre.name,
                                                            description = "Discover movie based ${resultedGenre.name}"
                                                        )
                                                    } ?: TitleAndDescriptionItemModelValue(
                                                        id = "title_and_description_genre",
                                                        title = "Data null",
                                                        description = "Data null",
                                                        tag = NullPointerException()
                                                    )
                                                }
                                                is LoadDataResult.Failed -> TitleAndDescriptionItemModelValue(
                                                    id = "title_and_description_genre",
                                                    title = "Error",
                                                    description = "${result.t}",
                                                    tag = result.t
                                                )
                                            }
                                        }
                                    )
                                }
                            )
                        )
                        newResult.add(SeparatorItemModelValue("separator_genre"))
                    }
                    newResult.addAllMovie(it.value)
                    LoadDataResult.Success(PagingResult(it.value.page, newResult, it.value.totalPages, it.value.totalResults))
                }
                is LoadDataResult.Failed -> LoadDataResult.Failed(it.t)
            }
        }
    }
}