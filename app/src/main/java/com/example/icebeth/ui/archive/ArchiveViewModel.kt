package com.example.icebeth.ui.archive

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val resultRepository: ResultRepository
) : ViewModel() {
    var state by mutableStateOf(ArchiveState())

    init {
        viewModelScope.launch {
            resultRepository.getAllResults().collect {
                state = state.copy(
                    results = it
                )
            }
        }
    }
}
