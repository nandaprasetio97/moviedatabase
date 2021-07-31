package com.nandaprasetio.moviedatabase.core.data.datasource.paging

import com.nandaprasetio.moviedatabase.core.data.repository.MovieRepository
import com.nandaprasetio.moviedatabase.core.domain.entity.Dimension
import com.nandaprasetio.moviedatabase.core.domain.entity.PagingResult
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail
import com.nandaprasetio.moviedatabase.core.ext.addAllMovie
import com.nandaprasetio.moviedatabase.core.presentation.LoadDataResult
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.*
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.ParallelLoadingCompoundModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.carouselcompoundmodelvalue.CarouselCompoundModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.carouselcompoundmodelvalue.CarouselCompoundPlaceholderModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.MovieDetailHeaderModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.SeparatorItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageandcontentitemmodelvalue.CarouselPosterImageAndContentItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageitemmodelvalue.CarouselPosterImageItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionItemModelValue

class MovieDetailPagingSource(
    private val movieRepository: MovieRepository,
    private val movieId: Int,
): BasedIntKeyedPagingSource<BaseModelValue>() {
    override suspend fun getPagingResult(page: Int): LoadDataResult<PagingResult<BaseModelValue>> {
        return when(page) {
            1 -> movieRepository.getMovieDetail(movieId).let {
                when (it) {
                    is LoadDataResult.Success -> {
                        val movieDetail: MovieDetail = it.value
                        val newResult: MutableList<BaseModelValue> = mutableListOf()
                        newResult.add(MovieDetailHeaderModelValue("movie_detail_header", movieDetail))
                        newResult.add(addMovieDetailBackdropsSection())
                        newResult.add(TitleAndDescriptionItemModelValue("title_and_description_overview", "Overview", movieDetail.overview))
                        newResult.add(SeparatorItemModelValue("separator_title_and_description_movie_detail_cast"))
                        newResult.add(TitleAndDescriptionItemModelValue("title_and_description_movie_detail_cast", "Cast", null))
                        newResult.add(addMovieDetailCastSection())
                        LoadDataResult.Success(PagingResult(1, newResult, 2, newResult.size))
                    }
                    is LoadDataResult.Failed -> LoadDataResult.Failed(it.t)
                }
            }
            else -> movieRepository.getMovieRecommendation(movieId, page - 1).let {
                when (it) {
                    is LoadDataResult.Success -> {
                        val newResult: MutableList<BaseModelValue> = mutableListOf()
                        val actualPage = page - 1
                        if (actualPage == 1) {
                            newResult.add(SeparatorItemModelValue("separator_recommendation_movie"))
                            newResult.add(TitleAndDescriptionItemModelValue("title_and_description_recommendation_movie", "Recommendation Movie", null))
                            newResult.add(SeparatorItemModelValue("separator_recommendation_movie_detail"))
                        }
                        it.value.let { result ->
                            val distinctResults = result.results.let { list ->
                                if (actualPage == 1) { list } else { list.takeLast(list.size - 1) }
                            }
                            val distinctPageResult = PagingResult(result.page, distinctResults, result.totalPages, distinctResults.size)
                            newResult.addAllMovie(distinctPageResult)
                            LoadDataResult.Success(PagingResult(distinctPageResult.page, newResult, distinctPageResult.totalPages, newResult.size))
                        }
                    }
                    is LoadDataResult.Failed -> LoadDataResult.Failed(it.t)
                }
            }
        }
    }

    private fun addMovieDetailBackdropsSection(): ParallelLoadingCompoundModelValue {
        val lastId = "movie_detail_backdrops"
        return ParallelLoadingCompoundModelValue(
            id = "parallel_loading_compound_${lastId}",
            oldItemModelValue = listOf(
                SeparatorItemModelValue(id = "separator_1_${lastId}"),
                CarouselCompoundPlaceholderModelValue("carousel_placeholders_${lastId}"),
                SeparatorItemModelValue(id = "separator_2_${lastId}")
            ),
            onParallelLoading = {
                movieRepository.getMovieDetailImage(movieId).let { result ->
                    when (result) {
                        is LoadDataResult.Success -> {
                            val backdropsList = result.value.backdrops.mapIndexed { index, movieDetailImage ->
                                CarouselPosterImageItemModelValue(
                                    id = "poster_image_${lastId}_${(index + 1)}",
                                    dimension = Dimension(movieDetailImage.width, movieDetailImage.height),
                                    image = movieDetailImage.filePath.toStringBasedSizeExpression("w300")
                                )
                            }.toMutableList().also { list -> if (list.size > 0) { list.removeAt(0) } }
                            CarouselCompoundModelValue(
                                id = "carousel_${lastId}",
                                itemModelValueList = backdropsList
                            ).let { carouselCompound ->
                                if (backdropsList.isNotEmpty()) {
                                    listOf(SeparatorItemModelValue(id = "separator_1_${lastId}"), carouselCompound, SeparatorItemModelValue(id = "separator_2_${lastId}"))
                                } else {
                                    listOf(SeparatorItemModelValue(id = "separator_1_${lastId}"))
                                }
                            }
                        }
                        is LoadDataResult.Failed -> listOf(
                            TitleAndDescriptionItemModelValue(
                                id = "title_and_description_error_${lastId}",
                                title = "Error",
                                description = result.t.message
                            )
                        )
                    }
                }
            }
        )
    }

    private fun addMovieDetailCastSection(): ParallelLoadingCompoundModelValue {
        val lastId = "movie_detail_cast"
        return ParallelLoadingCompoundModelValue(
            id = "parallel_loading_compound_${lastId}",
            oldItemModelValue = listOf(
                SeparatorItemModelValue(id = "separator_1_${lastId}"),
                CarouselCompoundPlaceholderModelValue("carousel_placeholders_${lastId}"),
                SeparatorItemModelValue(id = "separator_2_${lastId}")
            ),
            onParallelLoading = {
                movieRepository.getMovieDetailCredit(movieId).let { result ->
                    when (result) {
                        is LoadDataResult.Success -> {
                            val backdropsList = result.value.cast.mapIndexed { index, movieDetailImage ->
                                CarouselPosterImageAndContentItemModelValue(
                                    id = "poster_image_${lastId}_${(index + 1)}",
                                    dimension = Dimension(200, 300),
                                    image = movieDetailImage.profilePath?.toStringBasedSizeExpression("w200"),
                                    title = movieDetailImage.name,
                                    description = movieDetailImage.character
                                )
                            }.take(10).toMutableList().also { list -> if (list.size > 0) { list.removeAt(0) } }
                            CarouselCompoundModelValue(
                                id = "carousel_${lastId}",
                                itemModelValueList = backdropsList
                            ).let { carouselCompound ->
                                if (backdropsList.isNotEmpty()) {
                                    listOf(SeparatorItemModelValue(id = "separator_1_${lastId}", spacingDp = 5.0f), carouselCompound)
                                } else {
                                    listOf(SeparatorItemModelValue(id = "separator_1_${lastId}"))
                                }
                            }
                        }
                        is LoadDataResult.Failed -> listOf(
                            TitleAndDescriptionItemModelValue(
                                id = "title_and_description_error_${lastId}",
                                title = "Error",
                                description = result.t.message
                            )
                        )
                    }
                }
            }
        )
    }

    private fun addMovieDetailReviewSection(): ParallelLoadingCompoundModelValue {
        val lastId = "movie_detail_review"
        return ParallelLoadingCompoundModelValue(
            id = "parallel_loading_compound_${lastId}",
            oldItemModelValue = listOf(
                SeparatorItemModelValue(id = "separator_1_${lastId}"),
                CarouselCompoundPlaceholderModelValue("carousel_placeholders_${lastId}"),
                SeparatorItemModelValue(id = "separator_2_${lastId}")
            ),
            onParallelLoading = {
                movieRepository.getMovieDetailCredit(movieId).let { result ->
                    when (result) {
                        is LoadDataResult.Success -> {
                            val backdropsList = result.value.cast.mapIndexed { index, movieDetailImage ->
                                CarouselPosterImageAndContentItemModelValue(
                                    id = "poster_image_${lastId}_${(index + 1)}",
                                    dimension = Dimension(200, 300),
                                    image = movieDetailImage.profilePath?.toStringBasedSizeExpression("w200"),
                                    title = movieDetailImage.name,
                                    description = movieDetailImage.character
                                )
                            }.take(10).toMutableList().also { list -> if (list.size > 0) { list.removeAt(0) } }
                            CarouselCompoundModelValue(
                                id = "carousel_${lastId}",
                                itemModelValueList = backdropsList
                            ).let { carouselCompound ->
                                if (backdropsList.isNotEmpty()) {
                                    listOf(SeparatorItemModelValue(id = "separator_1_${lastId}", spacingDp = 5.0f), carouselCompound)
                                } else {
                                    listOf(SeparatorItemModelValue(id = "separator_1_${lastId}"))
                                }
                            }
                        }
                        is LoadDataResult.Failed -> listOf(
                            TitleAndDescriptionItemModelValue(
                                id = "title_and_description_error_${lastId}",
                                title = "Error",
                                description = result.t.message
                            )
                        )
                    }
                }
            }
        )
    }
}