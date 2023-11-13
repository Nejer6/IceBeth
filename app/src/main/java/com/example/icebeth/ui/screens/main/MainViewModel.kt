package com.example.icebeth.ui.screens.main

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
class MainViewModel @Inject constructor(
    resultRepository: ResultRepository,
    connectivityObserver: ConnectivityObserver
) : ViewModel() {
    val countOfResultsWithNullRemoteIdFlow = resultRepository.getCountOfResultsWithNullRemoteId()

    private val networkStatusFlow = connectivityObserver.observe()

    init {
        viewModelScope.launch {
            networkStatusFlow.collectLatest {
                if (it == ConnectivityObserver.Status.Available) {
                    delay(5000)
                    withContext(Dispatchers.IO) {
                        resultRepository.uploadResults()
                    }
                }
            }
        }
    }
}
