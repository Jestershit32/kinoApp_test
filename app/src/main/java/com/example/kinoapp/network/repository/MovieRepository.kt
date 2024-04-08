package com.example.kinoapp.network.repository

import com.example.kinoapp.network.models.ResponseBody
import com.example.kinoapp.network.models.SimpleMovieInfoByIdDomain
import com.example.kinoapp.network.models.SimpleMovieInfoDomain

interface MovieRepository {
    suspend fun searchMovie(
        page: Int,
    ): List<SimpleMovieInfoDomain>

    suspend fun searchMovieById(
        movie_id: Int,
    ): SimpleMovieInfoByIdDomain

    suspend fun authentication(
        login:String,
        password:String
    ):ResponseBody
}