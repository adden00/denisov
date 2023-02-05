package com.example.domain

import com.example.domain.models.FilmInfoItem
import com.example.domain.models.FilmItem

interface FilmsRepository {

    suspend fun loadFilms(): List<FilmItem>
    suspend fun loadFilmInfo(filmId: Int): FilmInfoItem?

}