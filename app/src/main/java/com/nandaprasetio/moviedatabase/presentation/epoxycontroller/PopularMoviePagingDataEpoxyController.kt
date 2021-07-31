package com.nandaprasetio.moviedatabase.presentation.epoxycontroller

import android.content.Context
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.misc.BooleanWrapper
import com.nandaprasetio.moviedatabase.core.presentation.EpoxyModelResult
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.TitleAndDescriptionEpoxyModel_
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionItemModelValue
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class PopularMoviePagingDataEpoxyController(
    onClickListener: ((Context, Movie) -> Unit)? = null,
    onAddModels: ((String, BaseModelValue) -> Unit)? = null
): MoviePagingDataEpoxyController(onClickListener, onAddModels) {
    override fun buildEachDefaultItemModel(currentPosition: Int, item: BaseModelValue?): EpoxyModelResult {
        return when (item) {
            is TitleAndDescriptionItemModelValue -> {
                if (item.id == "title_and_description_popular_movie") {
                    EpoxyModelResult.Model(
                        TitleAndDescriptionEpoxyModel_().id(item.id)
                            .spanSizeOverride { _, _, _ -> this.spanCount }
                            .title("Discover Popular Movie")
                            .description("Find what is popular movie.")
                    )
                } else {
                    super.buildEachDefaultItemModel(currentPosition, item)
                }
            }
            else -> super.buildEachDefaultItemModel(currentPosition, item)
        }
    }
}