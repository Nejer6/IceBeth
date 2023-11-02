package com.example.icebeth.feature.main

import androidx.lifecycle.ViewModel
import com.example.icebeth.core.data.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    resultRepository: ResultRepository
) : ViewModel() {

    val countOfResultsWithNullRemoteIdFlow = resultRepository.getCountOfResultsWithNullRemoteId()
}