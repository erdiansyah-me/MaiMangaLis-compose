package com.greil.maimangalis.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MangaListModel(
    val id: Int?,
    val title: String?,
    val imageUrl: String?,
) : Parcelable