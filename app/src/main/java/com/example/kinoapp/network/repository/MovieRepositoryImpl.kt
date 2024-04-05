package com.example.kinoapp.network.repository

import android.content.SharedPreferences
import com.drus.githubsearch.networking.NetworkService
import com.example.kinoapp.R
import com.example.kinoapp.network.models.PostBodyforLogin
import com.example.kinoapp.network.models.ResponseBody
import com.example.kinoapp.network.models.SimpleMovieInfo
import com.example.kinoapp.network.models.SimpleMovieInfoById
import com.example.kinoapp.utils.Constants
import com.example.kinoapp.utils.StringProvider
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val sharedPreferences: SharedPreferences,
    private val stringProvider: StringProvider
) : MovieRepository {

    private val TOKEN =
        Constants.TOKEN

    override suspend fun searchMovie(
        page: Int,
    ): List<SimpleMovieInfo> {
        val response = networkService.getMovies(
            sort_by = "popular",
            language = "ru",
            page = page,
            token = TOKEN
        )
        if (!response.isSuccessful)
            throw Exception(response.errorBody()?.string())
        return response.body()?.results ?: listOf()
    }

    override suspend fun searchMovieById(
        movie_id: Int
    ): SimpleMovieInfoById {
        val response = networkService.getDetailMovieById(
            movie_id = movie_id,
            language = "ru",
            token = TOKEN
        )
        if (!response.isSuccessful) throw Exception(response.errorBody()?.string())
        return response.body() ?: throw Exception(stringProvider.getString( R.string.exception_network))
    }

    override suspend fun authentication(
        login: String,
        password: String
    ): ResponseBody {
        val requestToken = networkService.getRequestToken(token = TOKEN)

        if (!requestToken.isSuccessful) throw Exception(requestToken.errorBody()?.string())
        val requestTokenNotNull = requestToken.body()?.request_token
            ?: throw Exception()
        val postBody = PostBodyforLogin(
            username = login,
            password = password,
            request_token = requestTokenNotNull
        )
        val response = networkService.confirmRequestTokenByLogin(token = TOKEN, postBody = postBody)
        if (!response.isSuccessful) throw Exception(stringProvider.getString(R.string.exception_message_login_password))
        sharedPreferences.edit()
            .putString(Constants.REQUEST_TOKEN, response.body()?.request_token)
            .putString(Constants.USERNAME, login)
            .apply()


        return response.body()
            ?: throw Exception(stringProvider.getString(R.string.exception_message_login_password))

    }

}
