package com.example.icebeth.ui.navigation

import androidx.lifecycle.ViewModel
import com.example.icebeth.core.data.preferences.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    appPreferences: AppPreferences
) : ViewModel() {
    val activeResultId = appPreferences.getActiveResultId()
}
