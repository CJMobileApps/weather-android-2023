package com.cjmobileapps.weatherandroid2023.data.weather

import com.cjmobileapps.weatherandroid2023.data.model.CurrentWeather
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import com.cjmobileapps.weatherandroid2023.util.onError
import com.cjmobileapps.weatherandroid2023.util.onSuccess
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

class WeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    private val tag = WeatherUseCase::class.java.simpleName

    private var currentWeather: CurrentWeather? = null

    suspend fun getLocationCoordinateFlow() = weatherRepository.getLocationCoordinateFlow()

    suspend fun fetchCoordinatesByLocationName(locationName: String): List<LocationCoordinate> {
        val locationCoordinates = arrayListOf<LocationCoordinate>()

        weatherRepository.getCoordinatesByLocationName(locationName = locationName)
            .onSuccess { locationCoordinatesResponse ->
                locationCoordinates.addAll(locationCoordinatesResponse)
            }
            .onError {
                Timber.tag(tag).e("fetchCoordinatesByLocationName() error occurred")
            }

        return locationCoordinates
            .distinctBy { it.name }
    }

    suspend fun getCurrentWeatherFlow() = weatherRepository.getCurrentWeatherFlow()

    suspend fun createLocationCoordinate(locationCoordinate: LocationCoordinate) {
        weatherRepository.createLocationCoordinate(locationCoordinate)
        fetchCurrentWeatherData(
            lat = locationCoordinate.lat,
            long = locationCoordinate.lon
        )
    }

    suspend fun fetchCurrentWeatherData(lat: Double, long: Double) {
        weatherRepository.getCurrentWeatherData(lat = lat, lon = long)
            .onSuccess { currentWeather ->
                if(this.currentWeather != currentWeather) {
                    weatherRepository.createCurrentWeather(currentWeather)
                    this.currentWeather = currentWeather
                }
            }
            .onError {
                Timber.tag(tag).e("fetchCurrentWeatherData() error occurred")
            }
    }

    suspend fun fetchReverseGeocoding(lat: Double, lon: Double): List<LocationCoordinate> {
        val locationCoordinates = arrayListOf<LocationCoordinate>()
        weatherRepository.reverseGeocoding(lat, lon)
            .onSuccess { locationCoordinatesResponse ->
                weatherRepository.createLocationCoordinate(locationCoordinatesResponse.first())
            }
            .onError {
                Timber.tag(tag).e("fetchCoordinatesByLocationName() error occurred")
            }

        return locationCoordinates
    }
}
