package com.example.icebeth.feature.active_result.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.domain.util.ResultError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultEditor(
    degreeOfCoverage: String,
    snowCoverCharacter: SnowCoverCharacter?,
    snowConditionDescription: SnowConditionDescription?,

    onDegreeOfCoverageChange: (String) -> Unit,
    onSnowCoverCharacterChange: (SnowCoverCharacter) -> Unit,
    onSnowConditionDescriptionChange: (SnowConditionDescription) -> Unit,

    degreeOfCoverageError: ResultError?,
    snowCoverCharacterError: ResultError?,
    snowConditionDescriptionError: ResultError?,

    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        OutlinedTextField(
            value = degreeOfCoverage,
            onValueChange = onDegreeOfCoverageChange,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Степень покрытия в баллах")
            },
            isError = degreeOfCoverageError != null,
            supportingText = {
                if (degreeOfCoverageError != null) {
                    Text(
                        text = when (degreeOfCoverageError) {
                            ResultError.Empty -> "Заполните поле"
                            ResultError.MoreThan10 -> "Введите число не больше 10"
                            ResultError.NegativeNumber -> "Введите неотрицательное число"
                            ResultError.NotInt -> "Введите целое число"
                        }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        SnowCoverCharacterDropDownMenu(
            snowCoverCharacter = snowCoverCharacter,
            onSnowCoverCharacterChange = onSnowCoverCharacterChange,
            snowCoverCharacterError = snowCoverCharacterError
        )

        SnowConditionDescriptionDropDownMenu(
            snowConditionDescription = snowConditionDescription,
            onSnowConditionDescriptionChange = onSnowConditionDescriptionChange,
            snowConditionDescriptionError = snowConditionDescriptionError
        )
    }
}