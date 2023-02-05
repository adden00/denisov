package com.example.data

import com.example.data.local.FilmDao
import com.example.data.models.toDomain
import com.example.data.models.toEntity
import com.example.data.network.FilmsNetworkService
import com.example.domain.FilmsRepository
import com.example.domain.models.FilmInfoItem
import com.example.domain.models.FilmItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmsRepositoryImpl(
    private val networkService: FilmsNetworkService,
    private val dao: FilmDao
) : FilmsRepository {
    override suspend fun loadFilms(): List<FilmItem> {
        return networkService.getFilms().map { it.toDomain() }
    }

    override suspend fun loadFilmInfo(filmId: Int): FilmInfoItem? {
        return networkService.getFilmInfo(filmId)?.toDomain()
    }

    override suspend fun insertFavouriteFilm(filmItem: FilmItem) {
        dao.insertFavouriteFilm(filmItem.toEntity())
    }

    override suspend fun getFavouriteFilms(): Flow<List<FilmItem>> {
        return dao.getFavouriteFilms().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun deleteFavouriteFilm(filmId: Int) {
        dao.deleteFavourFilm(filmId)
    }


}