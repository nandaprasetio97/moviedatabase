package com.nandaprasetio.moviedatabase.core.misc

import androidx.annotation.StringRes

data class ActivityConfiguration(
    @StringRes val titleResId: Int,
    val supportBack: Boolean
)