//package com.example.icebeth.feature.arhive
//
//import androidx.lifecycle.ViewModel
//import com.example.icebeth.core.data.repository.ResultRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.map
//import javax.inject.Inject
//
//@HiltViewModel
//class ArchiveViewModel @Inject constructor(
//    resultRepository: ResultRepository
//): ViewModel() {
//    val inactiveResultsFlow =
//        resultRepository.getAllInactiveResults().map { inactiveResults ->
//            inactiveResults.sortedByDescending { it.time }
//        }
//}