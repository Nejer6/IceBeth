package com.example.icebeth.ui.resultedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.ui.resultedit.navigation.ResultEditArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@HiltViewModel
class ResultEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val resultRepository: ResultRepository
) : ViewModel() {
    private val resultEditArgs = ResultEditArgs(savedStateHandle)

    var state by mutableStateOf(
        runBlocking {
            val result = resultRepository.getResultById(resultEditArgs.resultId).first()
            ResultEditState(
                degreeOfCoverage = result.degreeOfCoverage.toString(),
                snowConditionDescription = result.snowConditionDescription,
                snowCoverCharacter = result.snowCoverCharacter
            )
        }
    )

    fun onEvent(event: ResultEditEvent) {
        when (event) {
            is ResultEditEvent.ChangeDegreeOfCoverage -> {
                state = state.copy(
                    degreeOfCoverage = event.degreeOfCoverage
                )
            }
            is ResultEditEvent.ChangeSnowConditionDescription -> {
                state = state.copy(
                    snowConditionDescription = event.snowConditionDescription
                )
            }
            is ResultEditEvent.ChangeSnowCoverCharacter -> {
                state = state.copy(
                    snowCoverCharacter = event.snowCoverCharacter
                )
            }
            ResultEditEvent.Save -> TODO()
        }
    }
}
