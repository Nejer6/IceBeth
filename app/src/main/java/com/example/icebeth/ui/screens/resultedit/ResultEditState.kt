package com.example.icebeth.ui.screens.resultedit

import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.domain.util.ResultError

data class ResultEditState(
    val degreeOfCoverage: String,
    val snowCoverCharacter: SnowCoverCharacter,
    val snowConditionDescription: SnowConditionDescription,

    val degreeOfCoverageError: ResultError? = null
)
