package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.posterimageandcontentitemmodelvalue

import android.content.Context
import com.nandaprasetio.moviedatabase.core.domain.entity.Dimension

data class PosterImageAndContentItemModelValue(
    override val id: String,
    override val image: Any?,
    override val dimension: Dimension? = null,
    override val title: String?,
    override val description: String?,
    override val onClickListener: ((Context) -> Unit)? = null,
    override val tag: Any? = null
): BasePosterImageAndContentItemModelValue()