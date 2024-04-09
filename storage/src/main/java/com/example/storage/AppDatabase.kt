package com.example.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.storage.converters.ListStringConverter
import com.example.storage.entitys.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 3)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): Dao
}