package com.example.testtasktinkofffintech.presentation.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FilmInfoItem
import com.example.domain.usecases.GetFilmInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getFilmInfoUseCase: GetFilmInfoUseCase) :
    ViewModel() {

    private val _filmInfo = MutableStateFlow<FilmInfoItem?>(null)
    val filmInfo: StateFlow<FilmInfoItem?> = _filmInfo.asStateFlow()

    private val _isLoadingInfo = MutableStateFlow(false)
    val isLoadingInfo: StateFlow<Boolean> = _isLoadingInfo.asStateFlow()


    fun loadFilmInfo(filmId: Int) {
        _isLoadingInfo.value = true
        viewModelScope.launch {
            val result = getFilmInfoUseCase(filmId)
            _filmInfo.value = result
            _isLoadingInfo.value = false
        }
    }
}