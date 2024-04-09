package com.example.kinoapp.networking


import com.example.kinoapp.networking.models.MovieInfoList
import com.example.kinoapp.networking.models.PostBodyforLogin
import com.example.kinoapp.networking.models.ResponseBody
import com.example.kinoapp.networking.models.SimpleMovieInfoById
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("movie/{sort_by}")
    suspend fun getMovies(
        @Header("Authorization") token: String,
        @Path("sort_by") sort_by: String,
        @Query("page") page: Int,
        @Query("language") language: String,
    ): Response<MovieInfoList>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovieById(
        @Header("Authorization") token: String,
        @Path("movie_id") movie_id: Int,
        @Query("language") language: String,
    ): Response<SimpleMovieInfoById>

    @GET("authentication/token/new")
    suspend fun getRequestToken(
        @Header("Authorization") token: String,
    ): Response<ResponseBody>

    @POST("authentication/token/validate_with_login")
    suspend fun confirmRequestTokenByLogin(
        @Header("Authorization") token: String,
        @Body postBody: PostBodyforLogin
    ): Response<ResponseBody>
}

