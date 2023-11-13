package com.example.icebeth.core.data.network.api

import com.example.icebeth.core.data.network.model.request.MeasurementCreateRequest
import com.example.icebeth.core.data.network.model.request.MeasurementUpdateRequest
import com.example.icebeth.core.data.network.model.response.MeasurementResponse
import com.example.icebeth.core.data.network.util.ApiResponse
import com.example.icebeth.core.data.network.util.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementApi @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun createMeasurement(
        measurementCreateRequest: MeasurementCreateRequest
    ): ApiResponse<MeasurementResponse> {
        return httpClient.safeRequest {
            post("misures/") {
                setBody(measurementCreateRequest)
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun updateMeasurement(
        measurementId: Int,
        measurementUpdateRequest: MeasurementUpdateRequest
    ) = httpClient.safeRequest<MeasurementResponse> {
        put("misures/$measurementId") {
            setBody(measurementUpdateRequest)
            contentType(ContentType.Application.Json)
        }
    }
}
