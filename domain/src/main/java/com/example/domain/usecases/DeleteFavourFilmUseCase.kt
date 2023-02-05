package com.example.domain.usecases

import com.example.domain.FilmsRepository

class DeleteFavourFilmUseCase(private val repository: FilmsRepository) {

    suspend operator fun invoke(filmId: Int) {
        repository.deleteFavouriteFilm(filmId)
    }
}