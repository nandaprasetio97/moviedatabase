package com.nandaprasetio.moviedatabase.core.di.module

import android.content.Context
import androidx.room.Room
import com.nandaprasetio.moviedatabase.core.data.datasource.database.DatabaseContract
import com.nandaprasetio.moviedatabase.core.data.datasource.database.MovieCatalogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideGiftWishlistDatabase(@ApplicationContext applicationContext: Context): MovieCatalogDatabase {
        return Room.databaseBuilder(
            applicationContext,
            MovieCatalogDatabase::class.java,
            DatabaseContract.DATABASE_NAME
        ).build()
    }
}