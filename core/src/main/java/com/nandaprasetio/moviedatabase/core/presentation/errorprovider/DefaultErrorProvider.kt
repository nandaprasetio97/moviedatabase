package com.nandaprasetio.moviedatabase.core.presentation.errorprovider

import android.content.Context
import com.nandaprasetio.moviedatabase.core.R
import retrofit2.HttpException
import java.io.IOException

class DefaultErrorProvider: BaseErrorProvider() {
    override fun provideErrorResult(t: Throwable, context: Context): ErrorProviderResult {
        return when(t) {
            is HttpException -> ErrorProviderResult(context.getString(R.string.something_failed_notification), t.message, null)
            is IOException -> ErrorProviderResult(
                context.getString(R.string.no_connection_internet_notification),
                context.getString(R.string.no_connection_internet_notification_detail),
                null
            )
            is NullPointerException -> ErrorProviderResult(
                context.getString(R.string.data_is_null),
                context.getString(R.string.data_is_null_detail),
                null
            )
            else -> ErrorProviderResult(
                context.getString(R.string.something_failed_notification),
                context.getString(R.string.something_failed_notification_detail),
                null
            )
        }
    }
}