package com.example.icebeth

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.icebeth.common.presentation.theme.IceBethTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var locationCallback: LocationCallback
    lateinit var locationProvider: FusedLocationProviderClient

    @Composable
    fun getUserLocation(context: Context): LatandLong {
        locationProvider = LocationServices.getFusedLocationProviderClient(context)

        var currentUserLocation by remember {
            mutableStateOf(LatandLong())
        }

        DisposableEffect(key1 = locationProvider) {
            locationCallback = object : LocationCallback() {
                @SuppressLint("MissingPermission")
                override fun onLocationResult(result: LocationResult) {
                    locationProvider.lastLocation
                        .addOnSuccessListener {  location ->
                            location?.let {
                                val lat = location.latitude
                                val long = location.longitude

                                currentUserLocation = LatandLong(lat, long)
                            }
                        }
                        .addOnFailureListener {
                            Log.e("Location_error", "${it.message}")
                        }
                }
            }

            if (context.hasLocationPermission()) {
                locationUpdate()
            } else {
//                askPermissions(
//                    context, REQUEST_LOCATION_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                )
            }
            //3
            onDispose {
                stopLocationUpdate()
            }
        }

        return currentUserLocation
    }

    @SuppressLint("MissingPermission")
    fun locationUpdate() {
        locationCallback.let {
            //An encapsulation of various parameters for requesting
            // location through FusedLocationProviderClient.
            val locationRequest: LocationRequest =
                LocationRequest.create().apply {
                    interval = TimeUnit.SECONDS.toMillis(60)
                    fastestInterval = TimeUnit.SECONDS.toMillis(30)
                    maxWaitTime = TimeUnit.MINUTES.toMillis(2)
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }
            //use FusedLocationProviderClient to request location update
            locationProvider.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    fun stopLocationUpdate() {
        try {
            //Removes all location updates for the given callback.
            val removeTask = locationProvider.removeLocationUpdates(locationCallback)
            removeTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("LOCATION_TAG", "Location Callback removed.")
                } else {
                    Log.d("LOCATION_TAG", "Failed to remove Location Callback.")
                }
            }
        } catch (se: SecurityException) {
            Log.e("LOCATION_TAG", "Failed to remove Location Callback.. $se")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
        setContent {
            IceBethTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    AppNavigation()

                    val location = getUserLocation(context = this)

                    Text(text = "${location.latitude}, ${location.longitude}")
                }
            }
        }
    }
}

data class LatandLong(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
