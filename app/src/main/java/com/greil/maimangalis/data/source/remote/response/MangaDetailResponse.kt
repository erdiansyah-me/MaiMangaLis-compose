package com.greil.maimangalis.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MangaDetailResponse(

	@field:SerializedName("data")
	val data: DataDetail? = null
)

data class GenresItemDetail(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class WebpDetail(

	@field:SerializedName("large_image_url")
	val largeImageUrl: String? = null,

	@field:SerializedName("small_image_url")
	val smallImageUrl: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
)

data class PropDetail(

	@field:SerializedName("from")
	val from: FromDetail? = null,

	@field:SerializedName("to")
	val to: ToDetail? = null
)

data class DemographicsItemDetail(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class AuthorsItemDetail(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class TitlesItemDetail(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class ImagesDetail(

	@field:SerializedName("jpg")
	val jpg: JpgDetail? = null,

	@field:SerializedName("webp")
	val webp: WebpDetail? = null
)

data class FromDetail(

	@field:SerializedName("month")
	val month: Int? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("day")
	val day: Int? = null
)

data class SerializationsItemDetail(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class DataDetail(

	@field:SerializedName("title_japanese")
	val titleJapanese: String? = null,

	@field:SerializedName("favorites")
	val favorites: Int? = null,

	@field:SerializedName("chapters")
	val chapters: Any? = null,

	@field:SerializedName("scored_by")
	val scoredBy: Int? = null,

	@field:SerializedName("title_synonyms")
	val titleSynonyms: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("themes")
	val themes: List<ThemesItemDetail?>? = null,

	@field:SerializedName("approved")
	val approved: Boolean? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItemDetail?>? = null,

	@field:SerializedName("popularity")
	val popularity: Int? = null,

	@field:SerializedName("members")
	val members: Int? = null,

	@field:SerializedName("title_english")
	val titleEnglish: String? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("publishing")
	val publishing: Boolean? = null,

	@field:SerializedName("serializations")
	val serializations: List<SerializationsItemDetail?>? = null,

	@field:SerializedName("images")
	val images: ImagesDetail? = null,

	@field:SerializedName("volumes")
	val volumes: Any? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("titles")
	val titles: List<TitlesItemDetail?>? = null,

	@field:SerializedName("published")
	val published: PublishedDetail? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("explicit_genres")
	val explicitGenres: List<Any?>? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("scored")
	val scored: Double? = null,

	@field:SerializedName("background")
	val background: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("authors")
	val authors: List<AuthorsItemDetail?>? = null,

	@field:SerializedName("demographics")
	val demographics: List<DemographicsItemDetail?>? = null
)

data class PublishedDetail(

	@field:SerializedName("string")
	val string: String? = null,

	@field:SerializedName("prop")
	val prop: PropDetail? = null,

	@field:SerializedName("from")
	val from: String? = null,

	@field:SerializedName("to")
	val to: Any? = null
)

data class JpgDetail(

	@field:SerializedName("large_image_url")
	val largeImageUrl: String? = null,

	@field:SerializedName("small_image_url")
	val smallImageUrl: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
)

data class ToDetail(

	@field:SerializedName("month")
	val month: Any? = null,

	@field:SerializedName("year")
	val year: Any? = null,

	@field:SerializedName("day")
	val day: Any? = null
)

data class ThemesItemDetail(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
