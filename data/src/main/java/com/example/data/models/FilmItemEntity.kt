package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmItemEntity(


    @PrimaryKey (autoGenerate = false)
    @ColumnInfo (name = "filmId")
    val filmId: Int,

    @ColumnInfo (name = "genres")
    val genres: List<GenreEntity>,

    @ColumnInfo (name = "nameRu")
    val nameRu: String,


    @ColumnInfo (name = "posterUrlPreview")
    val posterUrlPreview: String,


    @ColumnInfo (name = "year")
    val year: String
)

