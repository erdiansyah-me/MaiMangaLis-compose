package com.greil.maimangalis.domain.usecase

import com.greil.maimangalis.data.MangaRepository
import com.greil.maimangalis.data.State
import com.greil.maimangalis.domain.model.MangaDetailModel
import com.greil.maimangalis.domain.model.MangaListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaInteractor @Inject constructor(private val mangaRepository: MangaRepository) : MangaUseCase {
    override fun getListManga(): Flow<State<List<MangaListModel>>> {
        return mangaRepository.getListManga()
    }

    override fun getDetailManga(id: Int): Flow<State<MangaDetailModel>> {
        return mangaRepository.getDetailManga(id)
    }

    override fun getFavoriteManga(): Flow<List<MangaListModel>> = mangaRepository.getFavoriteManga()

    override suspend fun insertFavoriteManga(manga: MangaListModel) = mangaRepository.insertFavoriteManga(manga)

    override suspend fun deleteNonFavoriteManga(id: Int) = mangaRepository.deleteNonFavoriteManga(id)

    override suspend fun checkFavoriteManga(id: Int): Int = mangaRepository.checkFavoriteManga(id)
}