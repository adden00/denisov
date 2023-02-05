package com.example.data.di

import com.example.data.Constants.BASE_URL
import com.example.data.FilmsRepositoryImpl
import com.example.data.network.FilmApiClient
import com.example.data.network.FilmsNetworkService
import com.example.domain.FilmsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DataModule {

    @Provides
    @Singleton
    fun provideApiClient(): FilmApiClient {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(FilmApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmRepo(networkService: FilmsNetworkService): FilmsRepository {
        return FilmsRepositoryImpl(networkService)
    }
}

