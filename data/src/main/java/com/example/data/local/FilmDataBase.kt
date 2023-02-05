package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.models.FilmInfoItemEntity
import com.example.data.models.FilmItemEntity
import com.example.data.models.FilmsConverter

@Database (entities = [FilmItemEntity::class, FilmInfoItemEntity::class], version = 1)
@TypeConverters (FilmsConverter::class)
abstract class FilmDataBase: RoomDatabase() {
    abstract fun getDao(): FilmDao
}