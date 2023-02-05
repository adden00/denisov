package com.example.domain.usecases

import com.example.domain.FilmsRepository
import com.example.domain.models.FilmItem
import kotlinx.coroutines.flow.Flow

class GetFavouriteFilmsUseCase(private val repository: FilmsRepository) {
    suspend operator fun invoke(): Flow<List<FilmItem>> {
        return repository.getFavouriteFilms()
    }
}