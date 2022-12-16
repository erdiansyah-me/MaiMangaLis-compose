package com.greil.maimangalis.app.di

import android.content.Context
import androidx.room.Room
import com.greil.maimangalis.data.source.local.db.FavoriteMangaDao
import com.greil.maimangalis.data.source.local.db.FavoriteMangaDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : FavoriteMangaDb {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteMangaDb::class.java, "favoritemanga.db"
        ).build()
    }

    @Provides
    fun provideDao(favoriteMangaDb: FavoriteMangaDb) : FavoriteMangaDao {
        return favoriteMangaDb.favoriteMangaDao()
    }
}