package com.example.data.network

import android.util.Log
import com.example.data.models.FilmItemEntity
import javax.inject.Inject


class FilmsNetworkService @Inject constructor(private val apiClient: FilmApiClient) {
    suspend fun getFilms(): List<FilmItemEntity> {
        var result: ResponseFilmModel? = null
        try {
            result = apiClient.getFilms().body()
        }
        catch (e: java.lang.Exception) {
            Log.d( "MyLog", e.toString())
        }

        return result?.films ?: listOf()
    }
}