package com.example.storage.repository

import com.example.storage.entitys.FavoriteMovie

interface AppDatabaseRepository {
    suspend fun addInFavorite(favoriteMovie: FavoriteMovie)
    suspend fun removeFavorite(id: Int, userName: String): Int
    suspend fun getFavoriteById(id: Int, userName: String): FavoriteMovie?
    suspend fun getAllFavoriteMovie(userName: String): List<FavoriteMovie>
}