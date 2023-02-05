package com.example.data.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FilmsConverter {

    @TypeConverter
    fun fromCountryList(list: List<CountryEntity>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromCountryString(str: String): List<CountryEntity> {
        val listType: Type = object : TypeToken<List<CountryEntity>>() {}.type
        return Gson().fromJson(str, listType)
    }



    @TypeConverter
    fun fromGenreList(list: List<GenreEntity>): String {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun fromGenreString(str: String): List<GenreEntity> {
        val listType: Type = object : TypeToken<List<GenreEntity>>() {}.type
        return Gson().fromJson(str, listType)
    }
}