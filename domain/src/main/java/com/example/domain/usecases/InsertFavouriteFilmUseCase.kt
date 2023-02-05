package com.example.domain.usecases

import com.example.domain.FilmsRepository
import com.example.domain.models.FilmItem

class InsertFavouriteFilmUseCase(private val repository: FilmsRepository) {
    suspend operator fun invoke(film: FilmItem) {
        repository.insertFavouriteFilm(film)
    }
}