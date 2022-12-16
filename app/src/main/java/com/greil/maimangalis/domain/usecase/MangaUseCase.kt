package com.greil.maimangalis.domain.usecase

import com.greil.maimangalis.data.State
import com.greil.maimangalis.domain.model.MangaDetailModel
import com.greil.maimangalis.domain.model.MangaListModel
import kotlinx.coroutines.flow.Flow

interface MangaUseCase {
    fun getListManga(): Flow<State<List<MangaListModel>>>
    fun getDetailManga(id: Int): Flow<State<MangaDetailModel>>
    fun getFavoriteManga(): Flow<List<MangaListModel>>
    suspend fun insertFavoriteManga(manga: MangaListModel)
    suspend fun deleteNonFavoriteManga(id: Int)
    suspend fun checkFavoriteManga(id: Int): Int
}