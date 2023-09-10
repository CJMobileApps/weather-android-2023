package com.cjmobileapps.weatherandroid2023.data.datasource

import com.cjmobileapps.weatherandroid2023.data.model.CurrentWeather
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import com.cjmobileapps.weatherandroid2023.room.WeatherDao
import com.cjmobileapps.weatherandroid2023.util.coroutine.CoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WeatherLocalDataSource(
    private val weatherDao: WeatherDao,
    private val coroutineDispatchers: CoroutineDispatchers
) {

    suspend fun getLocationCoordinateFlow(): Flow<LocationCoordinate?> {
        return withContext(coroutineDispatchers.io) {
            weatherDao.getLocationCoordinate()
        }
    }

    suspend fun createLocationCoordinate(locationCoordinates: LocationCoordinate) {
        withContext(coroutineDispatchers.io) {
            weatherDao.deleteAllLocationCoordinate()
            weatherDao.insertLocationCoordinate(locationCoordinates)
        }
    }

    suspend fun createCurrentWeather(currentWeather: CurrentWeather) {
        withContext(coroutineDispatchers.io) {
            weatherDao.deleteCurrentWeather()
            weatherDao.insertCurrentWeather(currentWeather)
        }
    }

    suspend fun getCurrentWeatherFlow(): Flow<CurrentWeather?> {
        return withContext(coroutineDispatchers.io) {
            weatherDao.getCurrentWeather()
        }
    }
}
