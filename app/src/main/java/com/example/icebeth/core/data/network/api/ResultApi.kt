package com.example.icebeth.core.data.network.api

import com.example.icebeth.core.data.network.model.request.ResultCreateRequest
import com.example.icebeth.core.data.network.model.request.ResultUpdateRequest
import com.example.icebeth.core.data.network.model.response.ResultResponse
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
class ResultApi @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun createResult(resultCreateRequest: ResultCreateRequest) =
        httpClient.safeRequest<ResultResponse> {
            post("results/") {
                setBody(resultCreateRequest)
                contentType(ContentType.Application.Json)
            }
        }

    suspend fun updateResult(resultId: Int, resultUpdateRequest: ResultUpdateRequest) =
        httpClient.safeRequest<ResultResponse> {
            put("results/$resultId") {
                setBody(resultUpdateRequest)
                contentType(ContentType.Application.Json)
            }
        }
}
