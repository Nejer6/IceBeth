package com.example.icebeth

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.example.icebeth.ui.components.LocationPermissionTextProvider
import com.example.icebeth.ui.components.OnLifecycleEvent
import com.example.icebeth.ui.components.PermissionDialog
import com.example.icebeth.ui.navigation.AppNavigation
import com.example.icebeth.ui.theme.IceBethTheme
import com.example.icebeth.util.hasLocationPermission
import com.example.icebeth.util.openAppSettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IceBethTheme {
                var isFineLocationPermissionGranted by remember {
                    mutableStateOf(false)
                }
                var isCoarseLocationPermissionGranted by remember {
                    mutableStateOf(false)
                }

                val locationPermissionResultLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestMultiplePermissions(),
                        onResult = { perms ->
                            isFineLocationPermissionGranted =
                                perms[Manifest.permission.ACCESS_FINE_LOCATION] == true
                            isCoarseLocationPermissionGranted =
                                perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                        }
                    )

                LaunchedEffect(key1 = isCoarseLocationPermissionGranted) {
                    locationPermissionResultLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }

                OnLifecycleEvent { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME -> {
                            isFineLocationPermissionGranted = this.hasLocationPermission()
                        }

                        else -> {}
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()

                    if (!isFineLocationPermissionGranted) {
                        PermissionDialog(
                            permissionTextProvider = LocationPermissionTextProvider(),
                            isPermanentlyDeclined =
                            !shouldShowRequestPermissionRationale(
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ),
                            onDismiss = { },
                            onOkClick = {
                                locationPermissionResultLauncher.launch(
                                    arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION
                                    )
                                )
                            },
                            onGoToAppSettingsClick = ::openAppSettings
                        )
                    }
                }
            }
        }
    }
}
