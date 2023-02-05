package com.example.data.models

import com.example.data.models.CountryEntity
import com.example.data.models.FilmItemEntity
import com.example.data.models.GenreEntity
import com.example.domain.models.Country
import com.example.domain.models.FilmItem
import com.example.domain.models.Genre

fun FilmItemEntity.toDomain(): FilmItem {
    return FilmItem(
        this.countries.map { it.toDomain() },
        this.filmId,
        this.filmLength,
        this.genres.map { it.toDomain() },
        this.nameEn,
        this.nameRu,
        this.posterUrl,
        this.posterUrlPreview,
        this.rating,
        this.ratingChange,
        this.ratingVoteCount,
        this.year
    )
}

fun CountryEntity.toDomain() = Country(this.country)

fun GenreEntity.toDomain() = Genre(this.genre)
