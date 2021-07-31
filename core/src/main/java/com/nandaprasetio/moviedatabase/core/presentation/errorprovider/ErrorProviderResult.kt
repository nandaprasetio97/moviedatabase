package com.nandaprasetio.moviedatabase.core.presentation.errorprovider

import androidx.annotation.DrawableRes

class ErrorProviderResult(
    val title: String?,
    val message: String?,
    @DrawableRes val iconResId: Int? = null
)