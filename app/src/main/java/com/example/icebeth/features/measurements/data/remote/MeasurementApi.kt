package com.example.icebeth.features.measurements.data.remote

import com.example.icebeth.features.measurements.data.remote.request.MeasurementCreateRequest
import com.example.icebeth.features.measurements.data.remote.response.Measurement
import com.example.icebeth.shared.util.ApiResponse
import com.example.icebeth.shared.util.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
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

    suspend fun createMeasurement(measurementCreateRequest: MeasurementCreateRequest) =
        httpClient.safeRequest<Measurement> {
            post("misures/") {
                setBody(measurementCreateRequest)
                contentType(ContentType.Application.Json)
            }
        }
}