package com.example.kinoapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseBody(
    @SerializedName("success")
   val succes:Boolean,
    @SerializedName("expires_at")
    val expired_at:String,
    @SerializedName("request_token")
    val request_token:String
) : Parcelable
