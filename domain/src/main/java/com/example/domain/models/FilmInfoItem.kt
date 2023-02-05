package com.example.domain.models

data class FilmInfoItem(

    val kinopoiskId: Int,
    val nameRu: String,
    val description: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val posterUrl: String,
)
