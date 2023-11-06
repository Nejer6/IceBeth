package com.example.icebeth.feature.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.core.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    authRepository: AuthRepository,
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEffect>()
    val eventFlow = _eventFlow.asSharedFlow()

    val isAuthorized by mutableStateOf(authRepository.isAuthorized())

//    init {
//        viewModelScope.launch {
//            if (authRepository.isAuthorized()) {
//                _eventFlow.emit(
//                    UiEffect.Navigate(AppRoute.MainRoute.route)
//                )
//            } else {
//                _eventFlow.emit(
//                    UiEffect.Navigate(AppRoute.LoginScreen.route)
//                )
//            }
//        }
//
////        viewModelScope.launch {
////            when (authRepository.authenticate()) {
////                is ApiResponse.Success -> {
////                    _eventFlow.emit(
////                        UiEffect.Navigate(AppRoute.MainRoute.route)
////                    )
////                }
////                else -> {
////                    _eventFlow.emit(
////                        UiEffect.Navigate(AppRoute.LoginScreen.route)
////                    )
////                }
////            }
////        }
//    }
}
