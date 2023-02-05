package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.Constants.BASE_URL
import com.example.data.FilmsRepositoryImpl
import com.example.data.local.FilmDao
import com.example.data.local.FilmDataBase
import com.example.data.network.FilmApiClient
import com.example.data.network.FilmsNetworkService
import com.example.domain.FilmsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDatabase(@ApplicationContext context: Context): FilmDataBase {
        return Room.databaseBuilder(context, FilmDataBase::class.java, "film_dataBase").build()
    }

    @Provides
    @Singleton
    fun provideDao(database: FilmDataBase) = database.getDao()

    @Provides
    @Singleton
    fun provideFilmRepo(networkService: FilmsNetworkService, dao: FilmDao): FilmsRepository {
        return FilmsRepositoryImpl(networkService, dao)
    }
}

