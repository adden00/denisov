package com.example.testtasktinkofffintech.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FilmItem
import com.example.domain.usecases.GetPopularFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPopularFilmsUseCase: GetPopularFilmsUseCase): ViewModel() {

    private val _popularFilmsList = MutableStateFlow(listOf<FilmItem>())
    val popularFilmsList: StateFlow<List<FilmItem>> = _popularFilmsList.asStateFlow()

    private val _isLoadingFilms = MutableStateFlow(false)
    val isLoadingFilms: StateFlow<Boolean> = _isLoadingFilms.asStateFlow()


    init {
        loadFilms()
    }

    fun loadFilms() {
        _isLoadingFilms.value = true
        viewModelScope.launch {
            val result = getPopularFilmsUseCase()
            _popularFilmsList.value = result
            _isLoadingFilms.value = false
        }
    }



}