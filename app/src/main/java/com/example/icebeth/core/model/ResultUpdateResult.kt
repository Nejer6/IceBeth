package com.example.icebeth.core.model

import com.example.icebeth.core.domain.util.ResultError

data class ResultUpdateResult(
    val degreeOfCoverageError: ResultError? = null,
    val snowCoverCharacterError: ResultError? = null,
    val snowConditionDescriptionError: ResultError? = null,

    val isSuccess: Boolean
)
