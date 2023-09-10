package com.cjmobileapps.weatherandroid2023.ui

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.filters.MediumTest
import androidx.test.rule.GrantPermissionRule
import com.cjmobileapps.weatherandroid2023.testutil.launch
import com.cjmobileapps.weatherandroid2023.testutil.waitUntilTimeout
import org.junit.Rule
import org.junit.Test

@MediumTest
class WeatherUiLocationPermissionsGrantedTest {

    @get:Rule
    val composeRule = createEmptyComposeRule()

    @get:Rule
    val permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)


    @Test
    fun loadCurrentLocationThenSearchNewLocationThenGoBackToDetailScnren() = composeRule.launch<WeatherActivity>(
        intentFactory = {
            Intent(it, WeatherActivity::class.java)
        },
        onAfterLaunched = {

            // weather detail screen
            waitUntilTimeout(1000)
            onNodeWithTag("currentTempText").assertIsDisplayed()
            onNodeWithTag("mainDescriptionText").assertIsDisplayed()
            onNodeWithTag("feelsLikeText").assertIsDisplayed()
            onNodeWithTag("lowHighText").assertIsDisplayed()
            onNodeWithContentDescription("currentWeatherIcon").assertIsDisplayed()
            onNodeWithTag("weatherDetailTopBarIcon").apply {
                assertIsDisplayed()
                performClick()
            }

            // search screen
            waitUntilTimeout(1000)
            onNodeWithText("Search City")
                .performTextInput("London")
            waitUntilTimeout(3000)
            onAllNodes(hasText("London"))[1].performClick()

            // weather detail screen
            waitUntilTimeout(1000)
            onNodeWithTag("currentTempText").assertIsDisplayed()
            onNodeWithTag("mainDescriptionText").assertIsDisplayed()
            onNodeWithTag("feelsLikeText").assertIsDisplayed()
            onNodeWithTag("lowHighText").assertIsDisplayed()
            onNodeWithContentDescription("currentWeatherIcon").assertIsDisplayed()
            onNodeWithTag("weatherDetailTopBarIcon").apply {
                assertIsDisplayed()
                assertTextEquals("London")
            }
        }
    )
}
