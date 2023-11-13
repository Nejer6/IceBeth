package com.example.icebeth.ui.screens.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.ui.screens.result.navigation.ResultArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    resultRepository: ResultRepository
) : ViewModel() {
    private val resultArgs = ResultArgs(savedStateHandle)

    private val resultFlow = resultRepository.getResultById(resultArgs.resultId)

    var state by mutableStateOf(
        ResultState(
            result = runBlocking {
                resultFlow.first()
            }
        )
    )

    init {
        viewModelScope.launch {
            resultFlow.collectLatest {
                state = state.copy(
                    result = it
                )
            }
        }
    }
}
