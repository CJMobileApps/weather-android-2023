package com.cjmobileapps.weatherandroid2023.ui.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
fun getLocationPermissions(
    onLocationGranted: (location: Location) -> Unit,
    onLocationDenied: () -> Unit = { },
    coroutineScope: CoroutineScope,
    locationPermissionsState: MultiplePermissionsState,
    context: Context
) {
    val locationProviderClient: FusedLocationProviderClient?

    if (locationPermissionsState.allPermissionsGranted) {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        locationProviderClient.lastLocation.addOnSuccessListener { location ->
            onLocationGranted.invoke(location)
        }
    } else {
        if (locationPermissionsState.shouldShowRationale)  {
            onLocationDenied.invoke()
        } else {
            coroutineScope.launch {
                locationPermissionsState.launchMultiplePermissionRequest()
            }
        }
    }
}
