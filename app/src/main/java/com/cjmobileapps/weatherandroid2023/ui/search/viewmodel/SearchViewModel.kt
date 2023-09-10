package com.cjmobileapps.weatherandroid2023.ui.search.viewmodel

import android.location.Location
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import com.cjmobileapps.weatherandroid2023.ui.search.viewmodel.SearchViewModelImpl.SearchState

interface SearchViewModel {
    fun getState(): SearchState

    fun currentLocationClicked(location: Location)

    fun locationClicked(locationCoordinate: LocationCoordinate)

    fun getSearchNavRouteUiNavRouteUiState(): SearchViewModelImpl.SearchNavRouteUi?

    fun goToWeatherDetailScreenUi()

    fun resetNavRouteUiToIdle()

    fun updateSearchEditText(text: String)

    fun getSearchEditText(): String

    fun getLocationCoordinates(): List<LocationCoordinate>
}
