package com.example.domain.models

data class FilmItem(
    val filmId: Int,
    val genres: List<Genre>,
    val nameRu: String,
    val posterUrlPreview: String,
    val year: String
)