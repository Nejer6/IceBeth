package com.example.icebeth.feature.active_result

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ActiveResultRoute(
    navigateToMain: () -> Unit,
    viewModel: ActiveResultViewModel = hiltViewModel()
) {
    val measurementsCount by viewModel.measurementsCountFlow.collectAsState(initial = 0)

    ActiveResultScreen(
        onSaveMeasurement = viewModel::saveMeasurement,
        currentMeasurementNumber = viewModel.currentMeasurementNumber,
        measurementsCount = measurementsCount
    )
}

@Composable
fun ActiveResultScreen(
    onSaveMeasurement: () -> Unit,
    currentMeasurementNumber: Int?,
    measurementsCount: Int
) {
    Column {
        Text(text = currentMeasurementNumber.toString())
        Text(text = measurementsCount.toString())
        Button(onClick = onSaveMeasurement) {
            Text(text = "Добавить измерение")
        }
    }
}