package com.cjmobileapps.weatherandroid2023.hilt.module;

import com.cjmobileapps.weatherandroid2023.util.coroutine.CoroutineDispatchers;
import com.cjmobileapps.weatherandroid2023.util.coroutine.CoroutineDispatchersImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class CoroutinesModule {

    @Singleton
    @Provides
    CoroutineDispatchers coroutinesDispatchers() {
        return CoroutineDispatchersImpl.INSTANCE;
    }
}
