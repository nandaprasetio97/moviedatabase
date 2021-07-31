package com.nandaprasetio.moviedatabase.presentation.epoxycontroller

import android.content.Context
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.Genre
import com.nandaprasetio.moviedatabase.core.misc.BooleanWrapper
import com.nandaprasetio.moviedatabase.core.presentation.EpoxyModelResult
import com.nandaprasetio.moviedatabase.core.presentation.epoxycontroller.BasePagingDataEpoxyController
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.SingleTextCardEpoxyModel_
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.TitleAndDescriptionEpoxyModel_
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionItemModelValue
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class MovieGenrePagingDataEpoxyController(
    private val onClickListener: ((Context, Genre) -> Unit)? = null
): BasePagingDataEpoxyController<BaseModelValue>() {
    override fun buildEachDefaultItemModel(currentPosition: Int, item: BaseModelValue?): EpoxyModelResult {
        return when (item) {
            is TitleAndDescriptionItemModelValue -> {
                EpoxyModelResult.Model(
                    TitleAndDescriptionEpoxyModel_().id(item.id)
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                        .title("Genre")
                        .description("Movie genre.")
                )
            }
            else -> super.buildEachDefaultItemModel(currentPosition, item)
        }
    }

    override fun buildEachItemModel(currentPosition: Int, item: BaseModelValue?): EpoxyModelResult {
        return if (item is GenreItemModelValue) {
            EpoxyModelResult.Model(
                SingleTextCardEpoxyModel_().id("movie-genre-${item.genre.id}")
                    .spanSizeOverride { _, _, _ -> 1 }
                    .title(item.genre.name)
                    .onClickListener { context ->
                        item.genre.also { genre ->
                            onClickListener?.invoke(context, genre)
                        }
                    }
            )
        } else {
            EpoxyModelResult.Failed(item)
        }
    }
}