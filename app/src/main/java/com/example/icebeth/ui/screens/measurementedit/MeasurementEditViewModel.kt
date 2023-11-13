package com.example.icebeth.ui.screens.measurementedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.ui.screens.measurementedit.navigation.MeasurementEditArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.runBlocking

@HiltViewModel
class MeasurementEditViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val measurementEditArgs = MeasurementEditArgs(savedStateHandle)

    var state by mutableStateOf(
        runBlocking {
            val measurement =
                measurementRepository.getMeasurementById(measurementEditArgs.measurementId)
            MeasurementEditState(
                cylinderHeight = measurement.cylinderHeight?.toString(),
                iceCrustThickness = measurement.iceCrustThickness?.toString(),
                massOfSnow = measurement.massOfSnow?.toString(),
                snowCrust = measurement.snowCrust,
                snowHeight = measurement.snowHeight.toString(),
                snowLayerWaterCondition = measurement.snowLayerWaterSaturation?.toString(),
                soilSurfaceCondition = measurement.soilSurfaceCondition,
                thawedWaterLayerThickness = measurement.thawedWaterLayerThickness?.toString(),
                isExpandedMeasurement = measurement.cylinderHeight != null
            )
        }
    )

    fun onEvent(event: MeasurementEditEvent) {
        when (event) {
            is MeasurementEditEvent.ChangeCylinderHeight -> {
                state = state.copy(
                    cylinderHeight = event.value
                )
            }

            is MeasurementEditEvent.ChangeIceCrustThickness -> {
                state = state.copy(
                    iceCrustThickness = event.value
                )
            }

            is MeasurementEditEvent.ChangeMassOfSnow -> {
                state = state.copy(
                    massOfSnow = event.value
                )
            }

            is MeasurementEditEvent.ChangeSnowHeight -> {
                state = state.copy(
                    snowHeight = event.value
                )
            }

            is MeasurementEditEvent.ChangeSnowLayerWaterCondition -> {
                state = state.copy(
                    snowLayerWaterCondition = event.value
                )
            }

            is MeasurementEditEvent.ChangeSoilSurfaceCondition -> {
                state = state.copy(
                    soilSurfaceCondition = event.value
                )
            }

            is MeasurementEditEvent.ChangeThawedWaterLayerThickness -> {
                state = state.copy(
                    thawedWaterLayerThickness = event.value
                )
            }

            MeasurementEditEvent.ToggleSnowCrust -> {
                state = state.copy(
                    snowCrust = !state.snowCrust!!
                )
            }

            MeasurementEditEvent.Save -> TODO()
        }
    }
}
