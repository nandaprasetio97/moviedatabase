package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue

import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

data class ImageItemModelValue(
    val imageUrlString: ImageUrlString
): BaseItemModelValue()