package com.example.kinoapp.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kinoapp.localDb.converters.ListStringConverter
import com.example.kinoapp.localDb.entitys.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 3)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao(): Dao
}