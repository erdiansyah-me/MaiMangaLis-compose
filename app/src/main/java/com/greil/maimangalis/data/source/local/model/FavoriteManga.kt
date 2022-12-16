package com.greil.maimangalis.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favoritemanga")

class FavoriteManga(
   @ColumnInfo(name = "id")
   @PrimaryKey
   var id: Int?,

   @ColumnInfo(name = "title")
   var title: String?,

   @ColumnInfo(name = "imageUrl")
   var imageUrl: String?,
): Serializable