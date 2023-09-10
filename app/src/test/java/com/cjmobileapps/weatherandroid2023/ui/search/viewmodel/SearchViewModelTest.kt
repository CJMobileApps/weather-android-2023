package com.cjmobileapps.weatherandroid2023.ui.search.viewmodel

import android.location.Location
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import com.cjmobileapps.weatherandroid2023.data.weather.WeatherUseCase
import com.cjmobileapps.weatherandroid2023.testutil.BaseTest
import com.cjmobileapps.weatherandroid2023.testutil.TestCoroutineDispatchers
import com.cjmobileapps.weatherandroid2023.ui.detail.viewmodel.WeatherDetailViewModelImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito

class SearchViewModelTest : BaseTest() {

    lateinit var viewModel: SearchViewModel

    @Mock
    lateinit var weatherUseCase: WeatherUseCase

    @Mock
    lateinit var mockLocation: Location

    private val mockLatMountainView = 37.3856

    private val mockLonMountainView = -122.082

    private val mockLocationCoordinateMountainView = LocationCoordinate(
        id = 1,
        name = "Mountain View",
        localName = mapOf(
            "en" to "Mountain View",
            "zh" to "山景城",
            "ar" to "مونتن فيو",
            "ru" to "Маунтин-Вью"
        ),
        lat = 37.3893889,
        lon = 122.0832101,
        country = "US",
        state = "California"
    )

    private fun setupViewModel() {
        this.viewModel = SearchViewModelImpl(
            weatherUseCase = weatherUseCase,
            coroutineDispatchers = TestCoroutineDispatchers
        )
    }

    @Test
    fun `currentLocationClicked then resetNavRouteUiToIdle`(): Unit = runBlocking {

        // when
        Mockito.`when`(mockLocation.latitude).thenReturn(mockLatMountainView)
        Mockito.`when`(mockLocation.longitude).thenReturn(mockLonMountainView)

        // then
        setupViewModel()
        viewModel.currentLocationClicked(mockLocation)
        val state = (viewModel.getState() as SearchViewModelImpl.SearchState.SearchLoadedState)
        val searchNavRouteUi = state.searchNavRouteUi

        // verify
        Mockito.verify(weatherUseCase, Mockito.times(1)).fetchReverseGeocoding(
            lat = mockLatMountainView,
            lon = mockLonMountainView
        )
        Assertions.assertEquals(
            SearchViewModelImpl.SearchNavRouteUi.GoToWeatherDetailScreenUi,
            searchNavRouteUi.value
        )

        // then
        viewModel.resetNavRouteUiToIdle()

        // verify
        Assertions.assertEquals(
            SearchViewModelImpl.SearchNavRouteUi.Idle,
            searchNavRouteUi.value
        )
    }

    @Test
    fun `update searchEditText then click locationClicked then resetNavRouteUiToIdle`(): Unit =
        runBlocking {

            // given
            val mockSearchText = "Mountain View"

            // when
            Mockito.`when`(mockLocation.latitude).thenReturn(mockLatMountainView)
            Mockito.`when`(mockLocation.longitude).thenReturn(mockLonMountainView)
            Mockito.`when`(weatherUseCase.fetchCoordinatesByLocationName(mockSearchText))
                .thenReturn(
                    listOf(mockLocationCoordinateMountainView)
                )

            // then
            setupViewModel()
            viewModel.updateSearchEditText(mockSearchText)
            val state = (viewModel.getState() as SearchViewModelImpl.SearchState.SearchLoadedState)
            val currentLocationCoordinate = state.locationCoordinates.value.first()
            viewModel.locationClicked(currentLocationCoordinate)
            val searchNavRouteUi = state.searchNavRouteUi

            // verify
            Assertions.assertEquals(
                mockSearchText,
                state.searchEditText.value
            )
            Assertions.assertEquals(
                mockLocationCoordinateMountainView,
                currentLocationCoordinate
            )
            Mockito.verify(weatherUseCase, Mockito.times(1)).fetchCoordinatesByLocationName(
                locationName = mockSearchText
            )
            Mockito.verify(weatherUseCase, Mockito.times(1)).createLocationCoordinate(
                locationCoordinate = mockLocationCoordinateMountainView
            )
            Assertions.assertEquals(
                SearchViewModelImpl.SearchNavRouteUi.GoToWeatherDetailScreenUi,
                searchNavRouteUi.value
            )

            // then
            viewModel.resetNavRouteUiToIdle()

            // verify
            Assertions.assertEquals(
                SearchViewModelImpl.SearchNavRouteUi.Idle,
                searchNavRouteUi.value
            )

        }
}
