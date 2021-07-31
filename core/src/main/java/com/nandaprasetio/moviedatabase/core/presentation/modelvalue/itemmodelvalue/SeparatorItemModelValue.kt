package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue

data class SeparatorItemModelValue(
    override val id: String,
    val spacingDp: Float = 16.0f,
    override val tag: Any? = null
): BaseItemModelValue()