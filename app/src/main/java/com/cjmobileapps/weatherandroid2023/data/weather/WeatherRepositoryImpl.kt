package com.cjmobileapps.weatherandroid2023.data.weather

import com.cjmobileapps.weatherandroid2023.data.datasource.WeatherApiDataSource
import com.cjmobileapps.weatherandroid2023.data.datasource.WeatherLocalDataSource
import com.cjmobileapps.weatherandroid2023.data.model.CurrentWeather
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val weatherApiDataSource: WeatherApiDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource
) : WeatherRepository {

    override suspend fun getCoordinatesByLocationName(locationName: String) =
        weatherApiDataSource.getCoordinatesByLocationName(locationName = locationName)

    override suspend fun getLocationCoordinateFlow() =
        weatherLocalDataSource.getLocationCoordinateFlow()

    override suspend fun createLocationCoordinate(locationCoordinates: LocationCoordinate) =
        weatherLocalDataSource.createLocationCoordinate(locationCoordinates)

    override suspend fun getCurrentWeatherData(lat: Double, lon: Double) =
        weatherApiDataSource.getCurrentWeatherData(
            lat = lat,
            lon = lon
        )

    override suspend fun reverseGeocoding(
        lat: Double,
        lon: Double
    ) = weatherApiDataSource.reverseGeocoding(
        lat = lat,
        lon = lon
    )

    override suspend fun createCurrentWeather(currentWeather: CurrentWeather) =
        weatherLocalDataSource.createCurrentWeather(currentWeather = currentWeather)

    override suspend fun getCurrentWeatherFlow(): Flow<CurrentWeather?> = weatherLocalDataSource.getCurrentWeatherFlow()
}
