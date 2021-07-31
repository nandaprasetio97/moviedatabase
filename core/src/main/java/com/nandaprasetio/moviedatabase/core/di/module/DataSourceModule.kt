package com.nandaprasetio.moviedatabase.core.di.module

import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.LocalMovieDataSourceImpl
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSource
import com.nandaprasetio.moviedatabase.core.data.datasource.content.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindMovieDataSourceImpl(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource

    @Binds
    @Singleton
    abstract fun bindLocalMovieDataSourceImpl(localMovieDataSourceImpl: LocalMovieDataSourceImpl): LocalMovieDataSource
}