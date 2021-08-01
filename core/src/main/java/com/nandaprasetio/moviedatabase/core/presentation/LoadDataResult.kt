package com.nandaprasetio.moviedatabase.core.presentation

sealed class LoadDataResult<T> {
    data class Success<T>(val value: T): LoadDataResult<T>()
    data class Failed<T>(val t: Throwable): LoadDataResult<T>()
}