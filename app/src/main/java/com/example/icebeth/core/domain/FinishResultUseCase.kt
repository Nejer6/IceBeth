package com.example.icebeth.core.domain

import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.domain.util.ResultError
import com.example.icebeth.core.model.ResultUpdateResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinishResultUseCase @Inject constructor(
    private val resultRepository: ResultRepository
) {
    suspend operator fun invoke(
        resultId: Int,
        degreeOfCoverage: String,
        snowCoverCharacter: SnowCoverCharacter?,
        snowConditionDescription: SnowConditionDescription?
    ): ResultUpdateResult {
        val degreeOfCoverageInt = degreeOfCoverage.toIntOrNull()

        val degreeOfCoverageError = when {
            degreeOfCoverage.isBlank() -> ResultError.Empty
            degreeOfCoverageInt == null -> ResultError.NotInt
            degreeOfCoverageInt < 0 -> ResultError.NegativeNumber
            degreeOfCoverageInt > 10 -> ResultError.MoreThan10
            else -> null
        }

        val snowCoverCharacterError = when (snowCoverCharacter) {
            null -> ResultError.Empty
            else -> null
        }

        val snowConditionDescriptionError = when (snowConditionDescription) {
            null -> ResultError.Empty
            else -> null
        }

        if (degreeOfCoverageError == null &&
            snowCoverCharacterError == null &&
            snowConditionDescriptionError == null
        ) {
            resultRepository.updateResult(
                resultId = resultId,
                degreeOfCoverage = degreeOfCoverageInt!!,
                snowCoverCharacter = snowCoverCharacter!!,
                snowConditionDescription = snowConditionDescription!!,
                isUpdated = false
            )

            return ResultUpdateResult(isSuccess = true)
        }

        return ResultUpdateResult(
            degreeOfCoverageError = degreeOfCoverageError,
            snowCoverCharacterError = snowCoverCharacterError,
            snowConditionDescriptionError = snowConditionDescriptionError,
            isSuccess = false
        )
    }
}
