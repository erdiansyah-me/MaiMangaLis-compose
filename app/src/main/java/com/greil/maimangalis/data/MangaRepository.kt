package com.greil.maimangalis.data

import com.greil.maimangalis.data.source.local.db.FavoriteMangaDao
import com.greil.maimangalis.data.source.remote.RemoteDataSource
import com.greil.maimangalis.domain.model.MangaDetailModel
import com.greil.maimangalis.domain.model.MangaListModel
import com.greil.maimangalis.domain.repository.IMangaRepository
import com.greil.maimangalis.utils.asEntityFavorite
import com.greil.maimangalis.utils.asModel
import com.greil.maimangalis.utils.mapToDomain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MangaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dao: FavoriteMangaDao
) : IMangaRepository{
    override fun getListManga(): Flow<State<List<MangaListModel>>> {
        return flow<State<List<MangaListModel>>> {
            emit(State.Loading())
            val manga = remoteDataSource.getListManga()
            manga
                .onFailure {
                    emit(State.Error(it.message!!))
                }
                .onSuccess {
                    if (it.data != null) {
                        emit(State.Success(it.data.mapToDomain()))
                    } else {
                        emit(State.Error("Failed to Fetch Data"))
                    }
                }
        }.flowOn(Dispatchers.IO)
    }

    override fun getDetailManga(id: Int): Flow<State<MangaDetailModel>> {
        return flow<State<MangaDetailModel>> {
            emit(State.Loading())
            val manga = remoteDataSource.getDetailManga(id)
            manga
                .onFailure {
                    emit(State.Error(it.message!!))
                }
                .onSuccess {
                    if (it.data != null) {
                        emit(State.Success(it.data.mapToDomain()))
                    } else {
                        emit(State.Error("Failed to Fetch Data"))
                    }
                }
        }.flowOn(Dispatchers.IO)
    }

    override fun getFavoriteManga(): Flow<List<MangaListModel>> {
        return dao.getFavorite().map {
            it.map {
                it.asModel()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertFavoriteManga(manga: MangaListModel) {
        return withContext(Dispatchers.IO) {
            dao.insertFavoriteManga(manga.asEntityFavorite())
        }
    }

    override suspend fun deleteNonFavoriteManga(id: Int) {
        return withContext(Dispatchers.IO) {
            dao.deleteNonFavoriteManga(id)
        }
    }

    override suspend fun checkFavoriteManga(id: Int): Int {
        return withContext(Dispatchers.IO) {
            dao.checkFavoriteManga(id)
        }
    }
}