package com.nandaprasetio.moviedatabase.core.presentation.epoxyholder

import android.view.View
import com.nandaprasetio.moviedatabase.core.R

class SeparatorEpoxyHolder: KotlinEpoxyHolder() {
    val spacingView by bind<View>(R.id.view_spacing)
}