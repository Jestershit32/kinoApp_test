package com.example.kinoapp.networking.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfoList(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val results:List<SimpleMovieInfo>,
): Parcelable