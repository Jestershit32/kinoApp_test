package com.example.networking.repository

import com.example.networking.models.ResponseBody
import com.example.networking.models.SimpleMovieInfoByIdDomain
import com.example.networking.models.SimpleMovieInfoDomain

interface MovieRepository {
    suspend fun searchMovie(
        page: Int,
    ): List<SimpleMovieInfoDomain>

    suspend fun searchMovieById(
        movieId: Int,
    ): SimpleMovieInfoByIdDomain

    suspend fun authentication(
        login: String,
        password: String
    ): ResponseBody
}