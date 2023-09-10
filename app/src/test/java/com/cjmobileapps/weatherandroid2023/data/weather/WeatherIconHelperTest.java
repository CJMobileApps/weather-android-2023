package com.cjmobileapps.weatherandroid2023.data.weather;

import com.cjmobileapps.weatherandroid2023.testutil.BaseTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeatherIconHelperTest extends BaseTest {

    @Test
    public void getIconUrlWhenIconProvidedTest() {
        // given
        String mockWeatherIconUrl = "https://openweathermap.org/img/wn/01d@2x.png";
        String mockWeatherIcon = "01d";


        //then
        String weatherIconHelper = WeatherIconHelper.getIconUrl(mockWeatherIcon, null);

        //verify
        Assertions.assertEquals(
            mockWeatherIconUrl,
            weatherIconHelper
        );
    }

    @Test
    public void getIconUrlWhenIconDescriptionProvidedTest() {
        // given
        String mockWeatherIconUrl = "https://openweathermap.org/img/wn/01d@2x.png";
        String mockWeatherDescriptionIcon = "clear sky";


        //then
        String weatherIconHelper = WeatherIconHelper.getIconUrl(null, mockWeatherDescriptionIcon);

        //verify
        Assertions.assertEquals(
                mockWeatherIconUrl,
                weatherIconHelper
        );
    }
}
