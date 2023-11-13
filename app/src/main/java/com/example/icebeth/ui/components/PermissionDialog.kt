package com.example.icebeth.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    when {
                        isPermanentlyDeclined -> onGoToAppSettingsClick()
                        else -> onOkClick()
                    }
                }
            ) {
                Text(
                    text = when {
                        isPermanentlyDeclined -> "Выдать разрешение"
                        else -> "ОК"
                    }
                )
            }
        },
        title = {
            Text(text = "Требуется разрешение")
        },
        text = {
            Text(text = permissionTextProvider.getDescription(isPermanentlyDeclined))
        },
        modifier = modifier
    )
}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class LocationPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "Похоже, вы отказались от разрешения доступа приложения к данным о " +
                "местоположении устройства. Вы можете перейти в настройки приложения, " +
                "чтобы предоставить его."
        } else {
            "Этому приложению необходим доступ к данным о местоположении устройства."
        }
    }
}
