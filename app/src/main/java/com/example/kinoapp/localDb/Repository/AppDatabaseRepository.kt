package com.example.kinoapp.localDb.Repository

import com.example.kinoapp.localDb.entitys.FavoriteMovie

interface AppDatabaseRepository {
     fun addInFavorite(favoriteMovie: FavoriteMovie)
     fun removeFavorite(id:Int,userName:String):Int
     fun getFavoriteById(id:Int,userName:String):List<FavoriteMovie>
     fun getAllFavoriteMovie(userName: String):List<FavoriteMovie>
}