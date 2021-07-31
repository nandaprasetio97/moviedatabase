package com.nandaprasetio.moviedatabase.core.presentation

sealed class LoadDataResult<T> {
    class Success<T>(val value: T): LoadDataResult<T>()
    class Failed<T>(val t: Throwable): LoadDataResult<T>()
}