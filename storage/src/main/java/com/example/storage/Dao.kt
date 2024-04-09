package com.example.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.storage.entitys.FavoriteMovie


@Dao
 interface Dao {

    @Insert
    fun insertFavoritMovie(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM favorites WHERE userName=:userName")
    fun getAllFavoriteMovie(userName:String): List<FavoriteMovie>

    @Query("SELECT * FROM favorites WHERE id=:id AND userName=:userName")
    fun getFavoritMovieById(id:Int,userName:String): List<FavoriteMovie>

    @Query("DELETE FROM favorites WHERE id=:id AND userName=:userName")
    fun deleteFavoritMovieById(id:Int,userName:String): Int
}