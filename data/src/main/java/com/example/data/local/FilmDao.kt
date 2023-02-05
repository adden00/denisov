package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.data.models.FilmItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmDao {

    @Query ("SELECT * FROM FilmItemEntity")
    fun getFavouriteFilms(): Flow<List<FilmItemEntity>>

    @Insert (onConflict = REPLACE)
    suspend fun insertFavouriteFilm(film: FilmItemEntity)

    @Query ("DELETE FROM FilmItemEntity WHERE filmId IS :filmId")
    suspend fun deleteFavourFilm(filmId: Int)

}