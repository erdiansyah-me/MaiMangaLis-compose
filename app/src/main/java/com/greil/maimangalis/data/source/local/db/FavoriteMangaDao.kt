package com.greil.maimangalis.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greil.maimangalis.data.source.local.model.FavoriteManga
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMangaDao {
    @Query("SELECT * FROM favoritemanga")
    fun getFavorite(): Flow<List<FavoriteManga>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteManga(favoriteManga: FavoriteManga)

    @Query("DELETE FROM favoritemanga WHERE favoritemanga.id = :id")
    suspend fun deleteNonFavoriteManga(id: Int)

    @Query("SELECT count(*) FROM favoritemanga WHERE favoritemanga.id = :id")
    suspend fun checkFavoriteManga(id: Int) : Int
}