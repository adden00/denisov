package com.example.testtasktinkofffintech.di

import com.example.domain.FilmsRepository
import com.example.domain.usecases.GetFilmInfoUseCase
import com.example.domain.usecases.GetPopularFilmsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn (ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetPopularFilmsUseCase(repository: FilmsRepository) = GetPopularFilmsUseCase(repository)

    @Provides
    fun provideGetFilmInfoUseCase(repository: FilmsRepository) = GetFilmInfoUseCase(repository)
}