package com.example.data.network

import com.example.data.models.FilmItemEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

private const val apiKey = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
interface FilmApiClient {

    @Headers ("X-API-KEY: $apiKey")
    @GET ("api/v2.2/films/top/?type=TOP_100_POPULAR_FILMS")
    suspend fun getFilms(): Response<List<FilmItemEntity>>

}