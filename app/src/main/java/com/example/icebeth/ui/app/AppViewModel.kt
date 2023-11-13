package com.example.icebeth.ui.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.data.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class AppViewModel @Inject constructor(
    private val resultRepository: ResultRepository,
    connectivityObserver: ConnectivityObserver
) : ViewModel() {
    var state by mutableStateOf(AppState())

    init {
        viewModelScope.launch {
            connectivityObserver.observe().collectLatest {
                state = state.copy(
                    isNetworkAvailable = it == ConnectivityObserver.Status.Available
                )
            }
        }

        viewModelScope.launch {
            resultRepository.getCountOfResultsWithNullRemoteId().collect {
                state = state.copy(
                    isExistUnaploadedResults = it != 0
                )
            }
        }
    }

    fun upload() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(5000)
                resultRepository.uploadResults()
            }
        }
    }
}
