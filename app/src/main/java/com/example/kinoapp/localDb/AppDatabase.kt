package com.example.kinoapp.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kinoapp.localDb.entitys.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 3)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao(): Dao
}