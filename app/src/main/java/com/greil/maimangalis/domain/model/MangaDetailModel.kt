package com.greil.maimangalis.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MangaDetailModel(
    val id: Int?,
    val title: String?,
    val author: List<AuthorModel?>?,
    val imageUrl: String?,
    val status: String?,
    val score: Number?,
    val synopsis: String?,
    val background: String?,
) : Parcelable
