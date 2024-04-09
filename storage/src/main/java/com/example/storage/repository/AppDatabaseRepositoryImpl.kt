package com.example.storage.repository


import com.example.storage.AppDatabase
import com.example.storage.entitys.FavoriteMovie

import javax.inject.Inject


class AppDatabaseRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : AppDatabaseRepository {
    override fun addInFavorite(favoriteMovie: FavoriteMovie) =
        database.getDao().insertFavoritMovie(favoriteMovie)

    override fun removeFavorite(id: Int, userName: String): Int =
        database.getDao().deleteFavoritMovieById(id, userName)

    override fun getFavoriteById(id: Int, userName: String): FavoriteMovie? =
        database.getDao().getFavoritMovieById(id, userName).firstOrNull()

    override fun getAllFavoriteMovie(userName: String) =
        database.getDao().getAllFavoriteMovie(userName)


}