package com.example.domain.usecases

import com.example.domain.FilmsRepository
import com.example.domain.models.FilmItem

class GetPopularFilmsUseCase (private val repository: FilmsRepository) {

    suspend operator fun invoke(): List<FilmItem> {
        return repository.loadFilms()
    }
}