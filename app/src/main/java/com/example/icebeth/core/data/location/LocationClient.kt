package com.example.icebeth.core.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import com.example.icebeth.util.hasLocationPermission
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class LocationClient(
    private val context: Context
) {
    private val client = LocationServices.getFusedLocationProviderClient(context)

    fun isLocationAvailable(): Boolean {
        if (!context.hasLocationPermission()) {
            return false
        }

        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled && !isNetworkEnabled) {
            return false
        }

        return true
    }

    @SuppressLint("MissingPermission")
    fun getLocationUpdates(interval: Long): Flow<LatLong?> {
        return callbackFlow {
            if (!context.hasLocationPermission()) {
                throw LocationException("Missing location permission")
            }

//            val locationManager =
//                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//            val isNetworkEnabled =
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//            if (!isGpsEnabled && !isNetworkEnabled) {
//                throw LocationException("GPS is disabled")
//            }

            val request = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                interval
            ).build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch {
                            send(
                                LatLong(
                                    latitude = location.latitude,
                                    longitude = location.longitude
                                )
                            )
                        }
                    }
                }

                override fun onLocationAvailability(p0: LocationAvailability) {
                    super.onLocationAvailability(p0)
                    if (!p0.isLocationAvailable) {
                        launch {
                            send(null)
                        }
                    }
                }
            }

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }
    }

    class LocationException(message: String) : Exception(message)

    data class LatLong(
        val latitude: Double,
        val longitude: Double
    )
}
