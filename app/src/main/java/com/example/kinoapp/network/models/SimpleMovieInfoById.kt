package com.example.kinoapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable

@Parcelize
data class Companies(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logo_path: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val origin_country: String
) : Parcelable

@Parcelize
data class Countries(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String,
    @SerializedName("name")
    val name: String
) : Parcelable

@Parcelize
data class Language(
    @SerializedName("english_name")
    val english_name: String,
    @SerializedName("iso_3166_1")
    val iso_639_1: String,
    @SerializedName("name")
    val name: String
) : Parcelable

@Parcelize
data class Collection(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String
) : Parcelable


@Parcelize
data class SimpleMovieInfoById(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Collection,
    @SerializedName("budget")
    val budget: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<Companies>,
    @SerializedName("production_countries")
    val productionCountries: List<Countries>,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<Language>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
) : Parcelable


data class SimpleMovieInfoByIdDomain(
    val genres: List<String>,
    val id: Int,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val runtime: Int,
    val tagline: String,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int,
)

fun SimpleMovieInfoById.toDomain(): SimpleMovieInfoByIdDomain {
    val genreListString= mutableListOf("")


    this.genres.forEach { item->
        genreListString.add(item.name)
    }
    return SimpleMovieInfoByIdDomain(
        id=this.id,
        genres =genreListString,
        overview = this.overview,
        popularity=this.popularity,
        posterPath= this.posterPath,
        runtime = this.runtime,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}


