package com.example.storage.converters

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun fromGenreList(genreList: List<String>):String{
        return genreList.joinToString(",")
    }
    @TypeConverter
    fun toGenreList(genresString: String):List<String>{
        return genresString.split(",")
    }
}