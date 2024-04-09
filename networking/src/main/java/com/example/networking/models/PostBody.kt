package com.example.kinoapp.networking.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostBodyforLogin(
    @SerializedName("username")
    val username:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("request_token")
    val requestToken:String
) : Parcelable


@Parcelize
data class PostBodyforSessionId(
    @SerializedName("request_token")
    val requestToken:String
) : Parcelable