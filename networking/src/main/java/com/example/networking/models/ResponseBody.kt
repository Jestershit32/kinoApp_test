package com.example.networking.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseBody(
    @SerializedName("success")
    val succes: Boolean,
    @SerializedName("expires_at")
    val expiredAt: String,
    @SerializedName("request_token")
    val requestToken: String
) : Parcelable
