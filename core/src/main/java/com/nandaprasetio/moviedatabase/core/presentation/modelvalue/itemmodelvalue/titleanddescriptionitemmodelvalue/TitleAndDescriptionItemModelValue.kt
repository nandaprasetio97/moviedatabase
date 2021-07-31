package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue.titleanddescriptionitemmodelvalue

data class TitleAndDescriptionItemModelValue(
    override val id: String,
    override val title: String?,
    override val description: String?,
    override val tag: Any? = null
): BaseTitleAndDescriptionItemModelValue()