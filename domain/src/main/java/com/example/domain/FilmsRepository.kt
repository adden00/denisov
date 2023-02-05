package com.example.domain

import com.example.domain.models.FilmInfoItem
import com.example.domain.models.FilmItem
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    suspend fun loadFilms(): List<FilmItem>
    suspend fun loadFilmInfo(filmId: Int): FilmInfoItem?

    suspend fun insertFavouriteFilm(filmItem: FilmItem)
    suspend fun getFavouriteFilms(): Flow<List<FilmItem>>
    suspend fun deleteFavouriteFilm(filmId: Int)
}