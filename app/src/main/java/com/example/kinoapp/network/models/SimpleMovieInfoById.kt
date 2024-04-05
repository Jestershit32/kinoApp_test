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
    val backdrop_path: String,
    @SerializedName("belongs_to_collection")
    val belongs_to_collection: Collection,
    @SerializedName("budget")
    val budget: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("production_companies")
    val production_companies: List<Companies>,
    @SerializedName("production_countries")
    val production_countries: List<Countries>,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spoken_languages: List<Language>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Float,
    @SerializedName("vote_count")
    val vote_count: Int,
) : Parcelable

