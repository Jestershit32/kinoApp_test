package com.example.kinoapp.localDb.Repository


import com.example.kinoapp.localDb.AppDatabase
import com.example.kinoapp.localDb.entitys.FavoriteMovie

import javax.inject.Inject


class AppDatabaseRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : AppDatabaseRepository {
    override fun addInFavorite(favoriteMovie: FavoriteMovie) =
        database.getDao().insertFavoritMovie(favoriteMovie)

    override fun removeFavorite(id: Int, userName: String): Int =
        database.getDao().deleteFavoritMovieById(id, userName)

    override fun getFavoriteById(id: Int, userName: String) =
        database.getDao().getFavoritMovieById(id, userName)

    override fun getAllFavoriteMovie(userName: String) =
        database.getDao().getAllFavoriteMovie(userName)


}