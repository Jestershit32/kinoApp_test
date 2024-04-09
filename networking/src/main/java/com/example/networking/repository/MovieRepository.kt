package com.example.kinoapp.networking.repository

import com.example.kinoapp.networking.models.ResponseBody
import com.example.kinoapp.networking.models.SimpleMovieInfoByIdDomain
import com.example.kinoapp.networking.models.SimpleMovieInfoDomain

interface MovieRepository {
    suspend fun searchMovie(
        page: Int,
    ): List<SimpleMovieInfoDomain>

    suspend fun searchMovieById(
        movieId: Int,
    ): SimpleMovieInfoByIdDomain

    suspend fun authentication(
        login:String,
        password:String
    ): ResponseBody
}