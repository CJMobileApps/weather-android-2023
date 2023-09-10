package com.cjmobileapps.weatherandroid2023.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cjmobileapps.weatherandroid2023.data.model.CurrentWeather
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * from locationCoordinate")
    fun getLocationCoordinate(): Flow<LocationCoordinate>

    @Insert
    suspend fun insertLocationCoordinate(locationCoordinate: LocationCoordinate)

    @Query("DELETE FROM locationcoordinate")
    suspend fun deleteAllLocationCoordinate()

    @Query("SELECT * from currentWeather")
    fun getCurrentWeather(): Flow<CurrentWeather>

    @Insert
    suspend fun insertCurrentWeather(currentWeather: CurrentWeather)

    @Query("DELETE FROM currentWeather")
    suspend fun deleteCurrentWeather()
}
