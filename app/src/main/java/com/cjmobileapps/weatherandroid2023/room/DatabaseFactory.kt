package com.cjmobileapps.weatherandroid2023.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cjmobileapps.weatherandroid2023.data.model.CurrentWeather
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate

@Database(entities = [LocationCoordinate::class, CurrentWeather::class], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}

class DatabaseFactory {

    companion object {
        fun getWeatherDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                "weather-database"
            ).build()
        }
    }
}
