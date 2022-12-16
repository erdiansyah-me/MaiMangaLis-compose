package com.greil.maimangalis.utils

import com.greil.maimangalis.data.source.local.model.FavoriteManga
import com.greil.maimangalis.data.source.remote.response.AuthorsItemDetail
import com.greil.maimangalis.data.source.remote.response.DataDetail
import com.greil.maimangalis.data.source.remote.response.DataItem
import com.greil.maimangalis.domain.model.AuthorModel
import com.greil.maimangalis.domain.model.MangaDetailModel
import com.greil.maimangalis.domain.model.MangaListModel

fun DataItem.asModel(): MangaListModel =
    MangaListModel(
        id = malId,
        title = title,
        imageUrl = images?.jpg?.imageUrl
    )

fun List<DataItem>.mapToDomain(): List<MangaListModel>  = map { it.asModel() }

fun DataDetail.asModel() : MangaDetailModel =
    MangaDetailModel(
        id = malId,
        title = title,
        author = authors?.map { it?.asModel() },
        imageUrl = images?.jpg?.imageUrl,
        status = status,
        score = score,
        synopsis = synopsis,
        background = background
    )

fun DataDetail.mapToDomain() : MangaDetailModel = asModel()

fun FavoriteManga.asModel() : MangaListModel =
    MangaListModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
    )

fun MangaListModel.asEntityFavorite(): FavoriteManga =
    FavoriteManga(
        id = id,
        title = title,
        imageUrl = imageUrl,
    )

fun AuthorsItemDetail.asModel() : AuthorModel =
    AuthorModel(
        id = malId,
        type = type,
        name = name,
    )