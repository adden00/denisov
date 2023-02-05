package com.example.data.models

import com.example.data.models.CountryEntity
import com.example.data.models.FilmItemEntity
import com.example.data.models.GenreEntity
import com.example.domain.models.Country
import com.example.domain.models.FilmInfoItem
import com.example.domain.models.FilmItem
import com.example.domain.models.Genre

fun FilmItemEntity.toDomain(): FilmItem {
    return FilmItem(
        this.filmId,
        this.genres.map { it.toDomain() },
        this.nameRu,
        this.posterUrlPreview,
        this.year
    )
}

fun FilmInfoItemEntity.toDomain(): FilmInfoItem {
    return FilmInfoItem(
        this.kinopoiskId,
        this.nameRu,
        this.description,
        this.countries.map { it.toDomain() },
        this.coverUrl,
        this.genres.map {it.toDomain()},
        this.posterUrl
    )
}

fun CountryEntity.toDomain() = Country(this.country)

fun GenreEntity.toDomain() = Genre(this.genre)
