package com.greil.maimangalis.data.source.remote

import com.greil.maimangalis.data.source.remote.response.MangaDetailResponse
import com.greil.maimangalis.data.source.remote.response.MangaListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getListManga(): Result<MangaListResponse> {
        return try {
            val response = apiService.getMangaList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Kesalahan! Data Kosong"))
                }
            } else {
                Result.failure(Exception("Kesalahan! Data Gagal Dimuat"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Kesalahan! Tidak Dapat Menjangkau Data"))
        }
    }

    suspend fun getDetailManga(id: Int): Result<MangaDetailResponse> {
        return try {
            val response = apiService.getMangaDetail(id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Kesalahan! Data Kosong"))
                }
            } else {
                Result.failure(Exception("Kesalahan! Data Gagal Dimuat"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Kesalahan! Tidak Dapat Menjangkau Data"))
        }
    }
}