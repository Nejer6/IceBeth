package com.example.icebeth.core.data.network.api

import com.example.icebeth.core.data.network.model.request.MeasurementCreateRequest
import com.example.icebeth.core.data.network.model.request.MeasurementUpdateRequest
import com.example.icebeth.core.data.network.model.request.ResultRequest
import com.example.icebeth.core.data.network.model.response.MeasurementResponse
import com.example.icebeth.features.measurements.data.remote.response.Result
import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.common.util.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
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
    suspend fun getAllMeasurements(resultId: Int): ApiResponse<List<MeasurementResponse>> =
        httpClient.safeRequest {
            get("/results/${resultId}/misures")
        }

    suspend fun createMeasurement(measurementCreateRequest: MeasurementCreateRequest) =
        httpClient.safeRequest<MeasurementResponse> {
            post("misures/") {
                setBody(measurementCreateRequest)
                contentType(ContentType.Application.Json)
            }
        }

    suspend fun updateMeasurement(measurementUpdateRequest: MeasurementUpdateRequest, id: Int) =
        httpClient.safeRequest<MeasurementResponse> {
            put("misures/$id") {
                setBody(measurementUpdateRequest)
                contentType(ContentType.Application.Json)
            }
        }

    suspend fun deleteMeasurement(measurementId: Int) =
        httpClient.safeRequest<MeasurementResponse> {
            delete("misures/$measurementId")
        }

    suspend fun getAllResults() =
        httpClient.safeRequest<List<Result>> {
            get("results/")
        }

    suspend fun deleteResult(resultId: Int) =
        httpClient.safeRequest<Result> {
            delete("results/$resultId")
        }

    suspend fun createResult() =
        httpClient.safeRequest<Result> {
            post("results/") {
                setBody(ResultRequest())
                contentType(ContentType.Application.Json)
            }
        }
}