package com.nandaprasetio.moviedatabase.core.presentation.epoxycontroller

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.nandaprasetio.moviedatabase.core.misc.BooleanWrapper
import com.nandaprasetio.moviedatabase.core.misc.parallelloading.ParallelLoadingResult
import com.nandaprasetio.moviedatabase.core.presentation.EpoxyModelResult
import com.nandaprasetio.moviedatabase.core.presentation.epoxymodel.*
import com.nandaprasetio.moviedatabase.core.presentation.errorprovider.ErrorProviderResult
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.ParallelLoadingCompoundModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.carouselcompoundmodelvalue.CarouselCompoundModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.compoundmodelvalue.carouselcompoundmodelvalue.CarouselCompoundPlaceholderModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.ErrorItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.SeparatorItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionItemModelValue
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue.TitleAndDescriptionPlaceholderItemModelValue
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
abstract class BasePagingDataEpoxyController<T : BaseModelValue>(
    private val firstSeparator: Boolean = true,
    private val lastSeparator: Boolean = true,
    private val onAddModels: ((String, T) -> Unit)? = null
): PagingDataEpoxyController<T>() {
    var loading: Boolean = false
        set(value) {
            if (field != value) requestModelBuild()
            field = value
        }

    var error: ErrorProviderResult? = null
        set(value) {
            if (field != value) requestModelBuild()
            field = value
        }

    private var parallelLoadingResultList: List<ParallelLoadingResult> = mutableListOf()

    @Synchronized
    fun bindParallelLoadingResult(parallelLoadingResultList: List<ParallelLoadingResult>) {
        this.parallelLoadingResultList = parallelLoadingResultList
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        val modelMutableList: MutableList<EpoxyModel<*>> = mutableListOf()
        if (firstSeparator) {
            modelMutableList.add(
                SeparatorEpoxyModel_().id("separator-first")
                    .separatorItemModelValue(SeparatorItemModelValue("separator-first"))
                    .spanSizeOverride { _, _, _ -> this.spanCount }
            )
        }
        for (i in models.indices) {
            val model = models[i]
            var oldBaseModelValueFound = false
            parallelLoadingResultList.forEach { parallelLoadingResult ->
                if (parallelLoadingResult.oldBaseModelValue.isNotEmpty()) {
                    parallelLoadingResult.oldBaseModelValue.forEachIndexed { index, oldBaseModelValue ->
                        if (index == 0) {
                            if (model is SeparatorEpoxyModel) {
                                model.separatorItemModelValue?.tag.also {
                                    if (it is ParallelLoadingCompoundModelValue) {
                                        if (oldBaseModelValue.id == it.oldItemModelValue[index].id) {
                                            parallelLoadingResult.newBaseModelValue.forEach { newBaseModelValue ->
                                                @Suppress("UNCHECKED_CAST")
                                                modelMutableList.add(
                                                    buildItemModelInternal(modelMutableList.size - 1, newBaseModelValue as T)
                                                )
                                            }
                                            oldBaseModelValueFound = true
                                            return@forEach
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!oldBaseModelValueFound) {
                var addDefaultModel = false
                if (model is SeparatorEpoxyModel) {
                    model.separatorItemModelValue?.tag.also {
                        if (it is ParallelLoadingCompoundModelValue) {
                            it.oldItemModelValue.forEach { baseModelValue ->
                                @Suppress("UNCHECKED_CAST")
                                modelMutableList.add(
                                    buildItemModelInternal(modelMutableList.size - 1, baseModelValue as T)
                                )
                            }
                        } else {
                            addDefaultModel = true
                        }
                    } ?: this.also {
                        addDefaultModel = true
                    }
                } else {
                    addDefaultModel = true
                }
                if (addDefaultModel) {
                    modelMutableList.add(models[i])
                }
            }
        }

        when {
            loading -> {
                modelMutableList.add(
                    LoadingEpoxyModel_().id("loading-paging-data")
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                )
            }
            error != null -> {
                error?.also {
                    modelMutableList.add(
                        ErrorEpoxyModel_().id("error-paging-data")
                            .errorItemModelValue(ErrorItemModelValue(it) { this.retry() })
                            .spanSizeOverride { _, _, _ -> this.spanCount }
                    )
                }
            }
            lastSeparator -> modelMutableList.add(
                SeparatorEpoxyModel_().id("separator-last")
                    .separatorItemModelValue(SeparatorItemModelValue("separator-last"))
                    .spanSizeOverride { _, _, _ -> this.spanCount }
            )
        }

        super.addModels(modelMutableList)
    }

    override fun buildItemModel(currentPosition: Int, item: T?): EpoxyModel<*> {
        item?.also { onAddModels?.invoke(it.id, item) }
        return buildItemModelInternal(currentPosition, item)
    }

    private fun buildItemModelInternal(currentPosition: Int, item: T?): EpoxyModel<*> {
        return buildEachDefaultItemModel(currentPosition, item).let { modelResult ->
            when (modelResult) {
                is EpoxyModelResult.Failed -> {
                    buildEachItemModel(currentPosition, item).let { finalModelResult ->
                        when (finalModelResult) {
                            is EpoxyModelResult.Failed -> throw IllegalStateException(
                                "Not suitable epoxy model from desired itemModelValue: ${finalModelResult.item}"
                            )
                            is EpoxyModelResult.Model -> finalModelResult.epoxyModel
                        }
                    }
                }
                is EpoxyModelResult.Model -> modelResult.epoxyModel
            }
        }
    }

    protected open fun buildEachDefaultItemModel(currentPosition: Int, item: T?): EpoxyModelResult {
        return when (item) {
            is TitleAndDescriptionItemModelValue -> {
                EpoxyModelResult.Model(
                    TitleAndDescriptionEpoxyModel_().id(item.id)
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                        .title(item.title)
                        .description(item.description)
                )
            }
            is TitleAndDescriptionPlaceholderItemModelValue -> {
                EpoxyModelResult.Model(
                    TitleAndDescriptionPlaceholderEpoxyModel_().id(item.id)
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                )
            }
            is SeparatorItemModelValue -> {
                EpoxyModelResult.Model(
                    SeparatorEpoxyModel_().id(item.id)
                        .separatorItemModelValue(item)
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                )
            }
            is CarouselCompoundModelValue -> {
                EpoxyModelResult.Model(
                    @Suppress("UNCHECKED_CAST")
                    CarouselModel_().id(item.id)
                        .models(item.itemModelValueList.mapIndexed { index, baseItemModelValue -> buildItemModelInternal(index, baseItemModelValue as T) })
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                        .padding(Carousel.Padding.dp(16, 0, 16, 0, 0))
                )
            }
            is CarouselCompoundPlaceholderModelValue -> {
                EpoxyModelResult.Model(
                    CarouselPlaceholderEpoxyModel_().id(item.id)
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                )
            }
            is ParallelLoadingCompoundModelValue -> {
                EpoxyModelResult.Model(
                    SeparatorEpoxyModel_().id(item.id)
                        .separatorItemModelValue(SeparatorItemModelValue(item.id, tag = item))
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                )
            }
            else -> EpoxyModelResult.Failed(item)
        }
    }

    protected abstract fun buildEachItemModel(currentPosition: Int, item: T?): EpoxyModelResult
}