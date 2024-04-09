package com.example.storage.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovie(
    @PrimaryKey(autoGenerate = true)
    var idRoom: Int? = null,
    @ColumnInfo(name = "userName")
    var userName: String,
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("overview")
    val overview: String,
    @ColumnInfo("genres")
    val genres: List<String>,
    @ColumnInfo("runtime")
    val runtime: Int,
    @ColumnInfo("tags")
    val tags: String,
    @ColumnInfo("rating")
    val rating: Float,
    @ColumnInfo("poster_path")
    val posterPath: String,
)
