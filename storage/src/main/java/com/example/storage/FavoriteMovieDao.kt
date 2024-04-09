package com.example.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.storage.entitys.FavoriteMovie

@Dao
interface FavoriteMovieDao {
    @Insert
    suspend fun insertFavoritMovie(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM favorites WHERE userName=:userName")
    suspend fun getAllFavoriteMovie(userName: String): List<FavoriteMovie>

    @Query("SELECT * FROM favorites WHERE id=:id AND userName=:userName")
    suspend fun getFavoriteMovieById(id: Int, userName: String): List<FavoriteMovie>

    @Query("DELETE FROM favorites WHERE id=:id AND userName=:userName")
    suspend fun deleteFavoriteMovieById(id: Int, userName: String): Int
}