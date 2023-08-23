package com.example.icebeth.features.measurements.domain.use_case

import com.example.icebeth.features.measurements.data.MeasurementRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetResultsUseCase @Inject constructor(
    private val measurementRepository: MeasurementRepository
) {
    suspend operator fun invoke() =  measurementRepository.getAllResults()
}