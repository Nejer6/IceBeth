package com.example.icebeth.core.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val ACTIVE_RESULT_ID = "active_result_id"

@Singleton
class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getActiveResultId(): Int? {
        return sharedPreferences.getInt(ACTIVE_RESULT_ID, -1).let {
            if (it == -1) null
            else it
        }
    }

    fun setActiveResultId(activeResultId: Int?) {
        sharedPreferences.edit()
            .putInt(
                ACTIVE_RESULT_ID,
                activeResultId ?: -1
            )
            .apply()
    }
}