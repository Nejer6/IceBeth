package com.example.icebeth.ui.screens.resultedit

import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter

sealed class ResultEditEvent {
    data class ChangeDegreeOfCoverage(val degreeOfCoverage: String) : ResultEditEvent()
    data class ChangeSnowCoverCharacter(
        val snowCoverCharacter: SnowCoverCharacter
    ) : ResultEditEvent()
    data class ChangeSnowConditionDescription(
        val snowConditionDescription: SnowConditionDescription
    ) : ResultEditEvent()
    data object Save : ResultEditEvent()
}
