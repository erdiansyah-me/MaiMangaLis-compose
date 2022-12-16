package com.greil.maimangalis.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greil.maimangalis.data.source.local.model.FavoriteManga


@Database(entities = [FavoriteManga::class], version = 1, exportSchema = false)
abstract class FavoriteMangaDb: RoomDatabase() {
    abstract fun favoriteMangaDao(): FavoriteMangaDao
}