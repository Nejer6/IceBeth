package com.example.icebeth.features.measurements.domain.use_case

import com.example.icebeth.features.measurements.data.MeasurementRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteResultUseCase @Inject constructor(
    private val measurementRepository: MeasurementRepository
) {
    suspend operator fun invoke(
        resultId: Int
    ) = measurementRepository.deleteResult(resultId)

}