package com.example.icebeth.core.presentation.navigation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.shared.presentation.util.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefs: SharedPreferences
) : ViewModel() {
    private val _effectFlow = MutableSharedFlow<UiEffect>()
    val effectFlow = _effectFlow.asSharedFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.Logout -> {
                prefs.edit()
                    .putString("jwt", "")
                    .apply()

                viewModelScope.launch {
                    _effectFlow.emit(UiEffect.Logout)
                }
            }
        }
    }
}