package com.example.data.network

import com.example.data.models.FilmItemEntity

data class ResponseFilmModel(
    val pagesCount: Int,
    val films: List<FilmItemEntity>
)
