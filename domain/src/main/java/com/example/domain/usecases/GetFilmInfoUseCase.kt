package com.example.domain.usecases

import com.example.domain.FilmsRepository
import com.example.domain.models.FilmInfoItem

class GetFilmInfoUseCase(private val repository: FilmsRepository) {
    suspend operator fun invoke(filmId: Int): FilmInfoItem? {
        return repository.loadFilmInfo(filmId)
    }
}