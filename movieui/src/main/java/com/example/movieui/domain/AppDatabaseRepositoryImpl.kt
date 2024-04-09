package com.example.movieui.domain

import com.example.storage.AppDatabase
import com.example.storage.entitys.FavoriteMovie
import com.example.storage.repository.AppDatabaseRepository

import javax.inject.Inject
class AppDatabaseRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : AppDatabaseRepository {
    override suspend fun addInFavorite(favoriteMovie: FavoriteMovie) =
        database.getDao().insertFavoritMovie(favoriteMovie)

    override suspend fun removeFavorite(id: Int, userName: String): Int =
        database.getDao().deleteFavoriteMovieById(id, userName)

    override suspend fun getFavoriteById(id: Int, userName: String): FavoriteMovie? =
        database.getDao().getFavoriteMovieById(id, userName).firstOrNull()

    override suspend fun getAllFavoriteMovie(userName: String) =
        database.getDao().getAllFavoriteMovie(userName)


}