package com.nandaprasetio.moviedatabase.core.presentation.modelvalue.itemmodelvalue

import android.content.Context
import com.nandaprasetio.moviedatabase.core.presentation.errorprovider.ErrorProviderResult

data class ErrorItemModelValue(
    val errorProviderResult: ErrorProviderResult,
    val onRetry: ((Context) -> Unit)? = null
)