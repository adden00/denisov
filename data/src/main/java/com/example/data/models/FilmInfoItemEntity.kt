package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmInfoItemEntity(

    @PrimaryKey (autoGenerate = false)
    @ColumnInfo (name = "kinopoiskId")
    val kinopoiskId: Int,

    @ColumnInfo (name = "nameRu")
    val nameRu: String,

    @ColumnInfo (name = "description")
    val description: String,

    @ColumnInfo (name = "countries")
    val countries: List<CountryEntity>,

    @ColumnInfo (name = "coverUrl")
    val coverUrl: String?,

    @ColumnInfo (name = "genres")
    val genres: List<GenreEntity>,

    @ColumnInfo (name = "posterUrl")
    val posterUrl: String,
)