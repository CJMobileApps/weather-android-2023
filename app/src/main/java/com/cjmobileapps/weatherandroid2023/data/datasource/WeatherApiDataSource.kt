package com.cjmobileapps.weatherandroid2023.data.datasource

import com.cjmobileapps.weatherandroid2023.data.model.CurrentWeather
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import com.cjmobileapps.weatherandroid2023.network.WeatherApi
import com.cjmobileapps.weatherandroid2023.util.coroutine.CoroutineDispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherApiDataSource(
    private val weatherApi: WeatherApi,
    private val coroutineDispatchers: CoroutineDispatchers
) {

    suspend fun getCoordinatesByLocationName(locationName: String): Response<List<LocationCoordinate>> {
        return withContext(coroutineDispatchers.io) {
            weatherApi
                .getCoordinatesByLocationNameAsync(locationName = locationName)
                .await()
        }
    }

    suspend fun getCurrentWeatherData(lat: Double, lon: Double): Response<CurrentWeather> {
        return withContext(coroutineDispatchers.io) {
            weatherApi
                .getCurrentWeatherDataAsync(
                    lat = lat,
                    lon = lon
                )
                .await()
        }
    }

    suspend fun reverseGeocoding(lat: Double, lon: Double): Response<List<LocationCoordinate>> {
        return withContext(coroutineDispatchers.io) {
            weatherApi
                .reverseGeocodingAsync(
                    lat = lat,
                    lon = lon
                )
                .await()
        }
    }
}
