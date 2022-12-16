package com.greil.maimangalis.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorModel(
    val id: Int?,
    val type: String?,
    val name: String?
) : Parcelable
