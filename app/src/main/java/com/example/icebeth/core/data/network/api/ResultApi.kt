package com.example.icebeth.core.data.network.api

import com.example.icebeth.common.util.safeRequest
import com.example.icebeth.core.data.network.model.request.ResultCreateRequest
import com.example.icebeth.core.data.network.model.response.ResultResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post
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
}
