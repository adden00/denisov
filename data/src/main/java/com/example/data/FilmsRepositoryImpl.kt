package com.example.data

import com.example.data.models.toDomain
import com.example.data.network.FilmsNetworkService
import com.example.domain.FilmsRepository
import com.example.domain.models.FilmInfoItem
import com.example.domain.models.FilmItem

class FilmsRepositoryImpl (private val networkService: FilmsNetworkService): FilmsRepository  {
    override suspend fun loadFilms(): List<FilmItem> {
        return networkService.getFilms().map { it.toDomain() }
    }

    override suspend fun loadFilmInfo(filmId: Int): FilmInfoItem? {
        return networkService.getFilmInfo(filmId)?.toDomain()
    }
}