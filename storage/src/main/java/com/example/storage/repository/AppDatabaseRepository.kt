package com.example.storage.repository

import com.example.storage.entitys.FavoriteMovie

interface AppDatabaseRepository {
    fun addInFavorite(favoriteMovie: FavoriteMovie)
    fun removeFavorite(id: Int, userName: String): Int
    fun getFavoriteById(id: Int, userName: String): FavoriteMovie?
    fun getAllFavoriteMovie(userName: String): List<FavoriteMovie>
}