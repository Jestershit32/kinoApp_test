package com.example.networking.domain.models

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


