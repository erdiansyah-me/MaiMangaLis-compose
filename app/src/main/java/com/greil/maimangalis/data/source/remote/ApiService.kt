package com.greil.maimangalis.data.source.remote

import com.greil.maimangalis.data.source.remote.response.MangaDetailResponse
import com.greil.maimangalis.data.source.remote.response.MangaListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("manga")
    suspend fun getMangaList(): Response<MangaListResponse>

    @GET("manga/{id}")
    suspend fun getMangaDetail(
        @Path("id") id: Int
    ): Response<MangaDetailResponse>
}