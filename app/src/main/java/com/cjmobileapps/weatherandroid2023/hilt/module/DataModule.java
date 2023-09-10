package com.cjmobileapps.weatherandroid2023.hilt.module;

import android.content.Context;

import com.cjmobileapps.weatherandroid2023.data.datasource.WeatherApiDataSource;
import com.cjmobileapps.weatherandroid2023.data.datasource.WeatherLocalDataSource;
import com.cjmobileapps.weatherandroid2023.data.weather.WeatherRepository;
import com.cjmobileapps.weatherandroid2023.data.weather.WeatherRepositoryImpl;
import com.cjmobileapps.weatherandroid2023.data.weather.WeatherUseCase;
import com.cjmobileapps.weatherandroid2023.network.WeatherApi;
import com.cjmobileapps.weatherandroid2023.room.DatabaseFactory;
import com.cjmobileapps.weatherandroid2023.room.WeatherDao;
import com.cjmobileapps.weatherandroid2023.room.WeatherDatabase;
import com.cjmobileapps.weatherandroid2023.util.coroutine.CoroutineDispatchers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class DataModule {

    @Singleton
    @Provides
    WeatherApiDataSource weatherApiDataSource(
            WeatherApi weatherApi,
            CoroutineDispatchers coroutineDispatchers
    ) {
        return new WeatherApiDataSource(
                weatherApi,
                coroutineDispatchers
        );
    }

    @Singleton
    @Provides
    WeatherRepository weatherRepository(
            WeatherApiDataSource weatherApiDataSource,
            WeatherLocalDataSource weatherLocalDataSource
    ) {
        return new WeatherRepositoryImpl(
                weatherApiDataSource,
                weatherLocalDataSource
        );
    }

    @Singleton
    @Provides
    WeatherUseCase weatherUseCase(
            WeatherRepository weatherRepository
    ) {
        return new WeatherUseCase(
                weatherRepository
        );
    }


    @Singleton
    @Provides
    WeatherDatabase weatherDatabase(
            @ApplicationContext Context context
    ) {
        return DatabaseFactory.Companion.getWeatherDatabase(context);
    }


    @Singleton
    @Provides
    WeatherDao weatherDao(
            WeatherDatabase weatherDatabase
    ) {
        return weatherDatabase.weatherDao();
    }


    @Singleton
    @Provides
    WeatherLocalDataSource weatherLocalDataSource(
            WeatherDao weatherDao,
            CoroutineDispatchers coroutineDispatchers
    ) {
        return new WeatherLocalDataSource(
                weatherDao,
                coroutineDispatchers
        );
    }
}
