package com.example.kinoapp.network.repository

import android.content.SharedPreferences
import com.drus.githubsearch.networking.NetworkService
import com.example.kinoapp.R
import com.example.kinoapp.network.models.PostBodyforLogin
import com.example.kinoapp.network.models.ResponseBody
import com.example.kinoapp.network.models.SimpleMovieInfoByIdDomain
import com.example.kinoapp.network.models.SimpleMovieInfoDomain
import com.example.kinoapp.network.models.toDomain
import com.example.kinoapp.utils.Constants
import com.example.kinoapp.utils.StringProvider
import com.example.kinoapp.utils.exception.AuthException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val sharedPreferences: SharedPreferences,
    private val stringProvider: StringProvider
) : MovieRepository {
    override suspend fun searchMovie(
        page: Int,
    ): List<SimpleMovieInfoDomain> {
        val response = networkService.getMovies(
            sort_by = "popular",
            language = "ru",
            page = page,
            token = Constants.TOKEN
        )
        if (!response.isSuccessful)
            throw Exception(response.errorBody()?.string())
        return response.body()?.results?.toDomain() ?: listOf()
    }

    override suspend fun searchMovieById(
        movieId: Int
    ): SimpleMovieInfoByIdDomain {
        val response = networkService.getDetailMovieById(
            movie_id = movieId,
            language = "ru",
            token = Constants.TOKEN
        )
        if (!response.isSuccessful) throw Exception(response.errorBody()?.string())
        return response.body()?.toDomain() ?: throw Exception(stringProvider.getString( R.string.exception_network))
    }

    override suspend fun authentication(
        login: String,
        password: String
    ): ResponseBody {
        val requestToken = networkService.getRequestToken(token = Constants.TOKEN)

        if (!requestToken.isSuccessful) throw AuthException(stringProvider.getString(R.string.exception_network))
        val requestTokenNotNull = requestToken.body()?.request_token
            ?: throw AuthException(stringProvider.getString(R.string.exception_network))

        val postBody = PostBodyforLogin(
            username = login,
            password = password,
            request_token = requestTokenNotNull
        )
        val response = networkService.confirmRequestTokenByLogin(token = Constants.TOKEN, postBody = postBody)

        if (!response.isSuccessful) throw AuthException(stringProvider.getString(R.string.exception_message_login_password))

        sharedPreferences.edit()
            .putString(Constants.REQUEST_TOKEN, response.body()?.request_token)
            .putString(Constants.USERNAME, login)
            .apply()

        return response.body()
            ?: throw AuthException(stringProvider.getString(R.string.exception_message_login_password))

    }

}
