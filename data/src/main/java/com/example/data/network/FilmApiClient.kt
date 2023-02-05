package com.example.data.network

import com.example.data.models.FilmItemEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import com.example.data.Constants.API_KEY
import com.example.data.models.FilmInfoItemEntity
import retrofit2.http.Path


interface FilmApiClient {

    @Headers ("X-API-KEY: $API_KEY")
    @GET ("api/v2.2/films/top/?type=TOP_100_POPULAR_FILMS")
    suspend fun getFilms(): Response<ResponseFilmModel>

    @Headers ("X-API-KEY: $API_KEY")
    @GET ("api/v2.2/films/{filmId}")
    suspend fun getFilmInfo(@Path ("filmId") filmId: Int): Response<FilmInfoItemEntity>

}