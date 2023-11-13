package com.example.icebeth.ui.screens.resultedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.domain.UpdateResultUseCase
import com.example.icebeth.ui.screens.resultedit.navigation.ResultEditArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class ResultEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val resultRepository: ResultRepository,
    private val updateResultUseCase: UpdateResultUseCase
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

    private val _effect = Channel<ResultEditEffect>()
    val effect = _effect.receiveAsFlow()

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
            ResultEditEvent.Save -> {
                viewModelScope.launch {
                    val updateResult = updateResultUseCase(
                        degreeOfCoverage = state.degreeOfCoverage,
                        resultId = resultEditArgs.resultId,
                        snowCoverCharacter = state.snowCoverCharacter,
                        snowConditionDescription = state.snowConditionDescription
                    )

                    if (updateResult.isSuccess) {
                        _effect.send(ResultEditEffect.NavigateUp)
                    } else {
                        state = state.copy(
                            degreeOfCoverageError = updateResult.degreeOfCoverageError
                        )
                    }
                }
            }
        }
    }
}
