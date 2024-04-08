package com.example.kinoapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleMovieInfo(
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("genre_ids")
    val genreIds :List<Int>,
    @SerializedName("id")
    val id:Int,
    @SerializedName("original_language")
    val originalLanguage : String,
    @SerializedName("original_title")
    val originalTitle:String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity:Float,
    @SerializedName("poster_path")
    val posterPath:String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video:Boolean,
    @SerializedName("vote_average")
    val voteAverage :Float,
    @SerializedName("vote_count")
    val voteCount:Int,
) : Parcelable



data class SimpleMovieInfoDomain(
    val id: Int,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
)

fun List<SimpleMovieInfo>.toDomain():List<SimpleMovieInfoDomain>{
    return this.map {item->
        SimpleMovieInfoDomain(
            id=item.id,
            overview = item.overview,
            popularity=item.popularity,
            posterPath= item.posterPath,
            title = item.title,
        )
    }
}