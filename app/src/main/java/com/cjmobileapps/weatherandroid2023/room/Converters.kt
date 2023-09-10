package com.cjmobileapps.weatherandroid2023.room

import androidx.room.TypeConverter
import com.cjmobileapps.weatherandroid2023.data.model.Clouds
import com.cjmobileapps.weatherandroid2023.data.model.Coord
import com.cjmobileapps.weatherandroid2023.data.model.Main
import com.cjmobileapps.weatherandroid2023.data.model.Rain
import com.cjmobileapps.weatherandroid2023.data.model.Snow
import com.cjmobileapps.weatherandroid2023.data.model.Sys
import com.cjmobileapps.weatherandroid2023.data.model.Weather
import com.cjmobileapps.weatherandroid2023.data.model.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun toStringMap(value: String?): Map<String, String> {
        if(value.isNullOrEmpty() || value == "null") return mapOf()
        val mapType = object : TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(value: Map<String, String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWeather(value: String?): List<Weather>? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWeather(value: List<Weather>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCoord(value: String?): Coord? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<Coord>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromCoord(value: Coord?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMain(value: String?): Main? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<Main>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromMain(value: Main?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSnow(value: String?): Snow? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<Snow>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromSnow(value: Snow?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toRain(value: String?): Rain? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<Rain>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromRain(value: Rain?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSys(value: String?): Sys? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<Sys>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromSys(value: Sys?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWind(value: String?): Wind? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<Wind>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWind(value: Wind?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toClouds(value: String?): Clouds? {
        if(value.isNullOrEmpty() || value == "null") return null
        val type = object : TypeToken<Clouds>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromClouds(value: Clouds?): String {
        return Gson().toJson(value)
    }
}
