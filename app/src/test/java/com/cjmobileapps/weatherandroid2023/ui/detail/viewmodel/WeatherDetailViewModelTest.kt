package com.cjmobileapps.weatherandroid2023.ui.detail.viewmodel

import android.location.Location
import androidx.test.filters.SmallTest
import com.cjmobileapps.weatherandroid2023.data.model.Clouds
import com.cjmobileapps.weatherandroid2023.data.model.Coord
import com.cjmobileapps.weatherandroid2023.data.model.CurrentWeather
import com.cjmobileapps.weatherandroid2023.data.model.LocationCoordinate
import com.cjmobileapps.weatherandroid2023.data.model.Main
import com.cjmobileapps.weatherandroid2023.data.model.Sys
import com.cjmobileapps.weatherandroid2023.data.model.Weather
import com.cjmobileapps.weatherandroid2023.data.model.Wind
import com.cjmobileapps.weatherandroid2023.data.weather.WeatherUseCase
import com.cjmobileapps.weatherandroid2023.testutil.BaseTest
import com.cjmobileapps.weatherandroid2023.testutil.TestCoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times

class WeatherDetailViewModelTest : BaseTest() {

    lateinit var viewModel: WeatherDetailViewModel

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

    private val mockCurrentWeatherMountainView = CurrentWeather(
        currentWeatherId = 7,
        id = 5375480,
        coord = Coord(
            lon = -122.082,
            lat = 37.3856
        ),
        weather = listOf(
            Weather(
                id = 800,
                main="Clear",
                description = "clear sky",
                icon = "01d"
            )
        ),
        base = "stations",
        main = Main(
            temp=72.88,
            feelsLike=72.68,
            tempMin=61.02,
            tempMax=81.75,
            pressure=1017,
            humidity=60,
            seaLevel=null,
            grndLevel=null
        ),
        visibility = 10000,
        wind = Wind(
            speed = 6.91,
            deg = 340,
            gust = null
        ),
        rain = null,
        clouds = Clouds(all=0),
        dt = 1694281561,
        sys = Sys(
            type = 2,
            id = 2010364,
            country = "US",
            sunrise = 1694267123,
            sunset = 1694312782
        ),
        timezone = -25200,
        name = "Mountain View",
        cod = 200,
        snow = null
    )

    private fun setupViewModel() {
        this.viewModel = WeatherDetailViewModelImpl(
            weatherUseCase = weatherUseCase,
            coroutineDispatchers = TestCoroutineDispatchers
        )
    }

    @Test
    fun `first time onLocationGranted happy flow success`(): Unit = runBlocking {

        // given
        val mockLocationCoordinateFlow: Flow<LocationCoordinate?> = flow {
            emit(null)
            emit(mockLocationCoordinateMountainView)

        }

        val mockCurrentWeatherFlow: Flow<CurrentWeather?> = flow {
            emit(null)
            emit(mockCurrentWeatherMountainView)
        }

        // when
        Mockito.`when`(mockLocation.longitude).thenReturn(mockLonMountainView)
        Mockito.`when`(mockLocation.latitude).thenReturn(mockLatMountainView)
        Mockito.`when`(weatherUseCase.getLocationCoordinateFlow()).thenReturn(mockLocationCoordinateFlow)
        Mockito.`when`(weatherUseCase.getCurrentWeatherFlow()).thenReturn(mockCurrentWeatherFlow)

        // then
        setupViewModel()
        viewModel.onLocationGranted(mockLocation)
        val state = (viewModel.getState() as WeatherDetailViewModelImpl.WeatherDetailState.WeatherDetailLoadedState)
        val currentTemp = viewModel.getCurrentTemp()
        val currentWeatherMainDescription = viewModel.getCurrentWeatherMainDescription()
        val currentWeatherFeelsLike = viewModel.getCurrentWeatherFeelsLike()
        val currentWeatherLow = viewModel.getCurrentWeatherTempMin()
        val currentWeatherHigh = viewModel.getCurrentWeatherTempMax()
        val currentWeatherIconUrl = viewModel.getCurrentWeatherIconUrl()

        // verify
        Assertions.assertEquals(
            mockCurrentWeatherMountainView,
            state.currentWeather
        )
        Assertions.assertEquals(
            mockCurrentWeatherMountainView.main?.temp,
            currentTemp
        )
        Assertions.assertEquals(
            mockCurrentWeatherMountainView.weather?.first()?.main,
            currentWeatherMainDescription
        )
        Assertions.assertEquals(
            mockCurrentWeatherMountainView.main?.feelsLike,
            currentWeatherFeelsLike
        )
        Assertions.assertEquals(
            mockCurrentWeatherMountainView.main?.tempMin?.toInt(),
            currentWeatherLow
        )
        Assertions.assertEquals(
            mockCurrentWeatherMountainView.main?.tempMax?.toInt(),
            currentWeatherHigh
        )
        Assertions.assertEquals(
            "https://openweathermap.org/img/wn/01d@2x.png",
            currentWeatherIconUrl
        )
        Mockito.verify(weatherUseCase, times(1)).fetchReverseGeocoding(
            lat = mockLatMountainView,
            lon = mockLonMountainView
        )
    }

    @Test
    fun `first time onLocationDenied happy flow success`(): Unit = runBlocking {

        // given
        val mockLocationCoordinateFlow: Flow<LocationCoordinate?> = flow {
            emit(null)

        }

        val mockCurrentWeatherFlow: Flow<CurrentWeather?> = flow {
            emit(null)
        }

        // when
        Mockito.`when`(mockLocation.longitude).thenReturn(mockLonMountainView)
        Mockito.`when`(mockLocation.latitude).thenReturn(mockLatMountainView)
        Mockito.`when`(weatherUseCase.getLocationCoordinateFlow()).thenReturn(mockLocationCoordinateFlow)
        Mockito.`when`(weatherUseCase.getCurrentWeatherFlow()).thenReturn(mockCurrentWeatherFlow)

        // then
        setupViewModel()
        viewModel.onLocationDenied()
        val state = (viewModel.getState() as WeatherDetailViewModelImpl.WeatherDetailState.WeatherDetailLoadedState)
        val weatherDetailNavRouteUi = state.weatherDetailNavRouteUi

        //verify
        Assertions.assertEquals(
            null,
            state.currentWeather
        )
        Assertions.assertEquals(
            WeatherDetailViewModelImpl.WeatherDetailNavRouteUi.GoToSearchScreenUi,
            weatherDetailNavRouteUi.value
        )
    }

    @Test
    fun `goToSearchScreenUi then reset resetNavRouteUiToIdle`() = runBlocking {

        // given
        val mockLocationCoordinateFlow: Flow<LocationCoordinate?> = flow {
            emit(null)

        }

        val mockCurrentWeatherFlow: Flow<CurrentWeather?> = flow {
            emit(null)
        }

        // when
        Mockito.`when`(weatherUseCase.getLocationCoordinateFlow()).thenReturn(mockLocationCoordinateFlow)
        Mockito.`when`(weatherUseCase.getCurrentWeatherFlow()).thenReturn(mockCurrentWeatherFlow)

        // then
        setupViewModel()
        viewModel.goToSearchScreenUi()
        viewModel.resetNavRouteUiToIdle()
        val state = (viewModel.getState() as WeatherDetailViewModelImpl.WeatherDetailState.WeatherDetailLoadedState)
        val weatherDetailNavRouteUi = state.weatherDetailNavRouteUi

        //verify
        Assertions.assertEquals(
            null,
            state.currentWeather
        )
        Assertions.assertEquals(
            WeatherDetailViewModelImpl.WeatherDetailNavRouteUi.Idle,
            weatherDetailNavRouteUi.value
        )
    }
}
