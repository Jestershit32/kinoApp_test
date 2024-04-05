package com.example.kinoapp.network.repository

import com.example.kinoapp.network.models.ResponseBody
import com.example.kinoapp.network.models.SimpleMovieInfo
import com.example.kinoapp.network.models.SimpleMovieInfoById

interface MovieRepository {
    suspend fun searchMovie(
        page: Int,
    ): List<SimpleMovieInfo>

    suspend fun searchMovieById(
        movie_id: Int,
    ): SimpleMovieInfoById

    suspend fun authentication(
        login:String,
        password:String
    ):ResponseBody
}