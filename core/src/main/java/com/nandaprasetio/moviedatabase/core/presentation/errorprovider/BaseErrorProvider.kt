package com.nandaprasetio.moviedatabase.core.presentation.errorprovider

import android.content.Context

abstract class BaseErrorProvider {
    abstract fun provideErrorResult(t: Throwable, context: Context): ErrorProviderResult
}