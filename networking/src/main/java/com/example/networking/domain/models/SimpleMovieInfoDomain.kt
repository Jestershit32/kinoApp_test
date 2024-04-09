package com.example.networking.domain.models

import com.example.networking.models.SimpleMovieInfo

data class SimpleMovieInfoDomain(
    val id: Int,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
)


