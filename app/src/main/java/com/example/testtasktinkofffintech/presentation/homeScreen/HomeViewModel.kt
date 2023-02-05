package com.example.testtasktinkofffintech.presentation.homeScreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FilmItem
import com.example.domain.usecases.DeleteFavourFilmUseCase
import com.example.domain.usecases.GetFavouriteFilmsUseCase
import com.example.domain.usecases.GetPopularFilmsUseCase
import com.example.domain.usecases.InsertFavouriteFilmUseCase
import com.example.testtasktinkofffintech.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
        private val getFavouriteFilmsUseCase: GetFavouriteFilmsUseCase,
        private val insertFavouriteFilmUseCase: InsertFavouriteFilmUseCase,
        private val deleteFavourFilmUseCase: DeleteFavourFilmUseCase
    ): ViewModel() {

    private val _popularFilmsList = MutableStateFlow(listOf<FilmItem>())
    val popularFilmsList: StateFlow<List<FilmItem>> = _popularFilmsList.asStateFlow()

    private val _isLoadingFilms = MutableStateFlow(false)
    val isLoadingFilms: StateFlow<Boolean> = _isLoadingFilms.asStateFlow()

    private val _favouriteFilms = MutableStateFlow(listOf<FilmItem>())
    val favouriteFilms: StateFlow<List<FilmItem>> = _favouriteFilms.asStateFlow()

    private var allPopularFilms = listOf<FilmItem>()
    private var allFavouriteFilms = listOf<FilmItem>()

    init {
        loadFilms()
        loadFavouriteFilms()
    }

    fun loadFilms() {
        _isLoadingFilms.value = true
        viewModelScope.launch {
            val result = getPopularFilmsUseCase()
            _popularFilmsList.value = result
            _isLoadingFilms.value = false
            allPopularFilms = result
        }
    }


    private fun loadFavouriteFilms() {
        viewModelScope.launch {
            getFavouriteFilmsUseCase().collect(){
                _favouriteFilms.value = it
                allFavouriteFilms = it
            }
        }
    }

    fun insertFavouriteFilm(film: FilmItem) {
        viewModelScope.launch {
            insertFavouriteFilmUseCase(film)
        }
    }

    fun removeFavouriteFilm(filmId: Int) {
        viewModelScope.launch {
            deleteFavourFilmUseCase(filmId)
        }
    }

    fun filterFilms(filterText: String, flag: Int) {
        val result = mutableListOf<FilmItem>()
        when (flag) {
            Constants.POPULAR_FILM_FLAG -> {
                allPopularFilms.forEach {
                    if (it.nameRu.lowercase().contains(filterText.lowercase()))
                        result.add(it)
                }
                _popularFilmsList.value = result
            }
            Constants.FAVOURITE_FILM_FLAG -> {
                allFavouriteFilms.forEach {
                    if (it.nameRu.lowercase().contains(filterText.lowercase()))
                        result.add(it)
                }
                _favouriteFilms.value = result
            }
        }
    }
}