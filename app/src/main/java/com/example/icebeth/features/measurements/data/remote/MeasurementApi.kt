package com.example.icebeth.features.measurements.data.remote

import android.content.SharedPreferences
import com.example.icebeth.features.measurements.data.remote.response.Measurement
import com.example.icebeth.shared.util.ApiResponse
import com.example.icebeth.shared.util.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getAllMeasurements(): ApiResponse<List<Measurement>> =
        httpClient.safeRequest {
            get("misures/")
        }
}