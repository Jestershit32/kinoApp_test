package com.example.movieui.domain

import com.example.networking.domain.models.SimpleMovieInfoByIdDomain
import com.example.networking.domain.models.SimpleMovieInfoDomain
import com.example.networking.models.SimpleMovieInfo
import com.example.networking.models.SimpleMovieInfoById

fun SimpleMovieInfoById.toDomain(): SimpleMovieInfoByIdDomain {
    val genreListString = mutableListOf<String>()
    this.genres.forEach { item ->
        genreListString.add(item.name)
    }
    return SimpleMovieInfoByIdDomain(
        id = this.id,
        genres = genreListString,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        runtime = this.runtime,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun List<SimpleMovieInfo>.toDomain(): List<SimpleMovieInfoDomain> {
    return this.map { item ->
        SimpleMovieInfoDomain(
            id = item.id,
            overview = item.overview,
            popularity = item.popularity,
            posterPath = item.posterPath,
            title = item.title,
        )
    }
}