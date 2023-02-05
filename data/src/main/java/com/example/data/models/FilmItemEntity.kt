package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmItemEntity(

    @ColumnInfo (name = "countries")
    val countries: List<CountryEntity>,

    @PrimaryKey (autoGenerate = false)
    @ColumnInfo (name = "filmId")
    val filmId: Int,

    @ColumnInfo (name = "filmLength")
    val filmLength: String,

    @ColumnInfo (name = "genres")
    val genres: List<GenreEntity>,

    @ColumnInfo (name = "nameEn")
    val nameEn: String?,

    @ColumnInfo (name = "nameRu")
    val nameRu: String,

    @ColumnInfo (name = "posterUrl")
    val posterUrl: String,

    @ColumnInfo (name = "posterUrlPreview")
    val posterUrlPreview: String,

    @ColumnInfo (name = "rating")
    val rating: String,

    @ColumnInfo (name = "ratingChange")
    val ratingChange: String?,

    @ColumnInfo (name = "ratingVoteCount")
    val ratingVoteCount: Int,

    @ColumnInfo (name = "year")
    val year: String
)

