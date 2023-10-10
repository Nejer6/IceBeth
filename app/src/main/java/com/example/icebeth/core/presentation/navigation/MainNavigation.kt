package com.example.icebeth.core.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icebeth.features.measurements.presentation.results.ResultsRoute
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.common.presentation.util.MainRoute
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.feature.main.navigation.mainRoute
import com.example.icebeth.feature.main.navigation.mainScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val mainGraph = "main_graph"

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    navigate(mainGraph, navOptions)
}

fun NavGraphBuilder.mainGraph(
    navigate: (String) -> Unit,
    logout: () -> Unit,
    navigateToAddMeasurement: (Int, Measurement?) -> Unit
) {
    composable(mainGraph) {
        MainNavigation(
            navigate = navigate,
            logout = logout,
            navigateToAddMeasurement = navigateToAddMeasurement
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    navigate: (String) -> Unit,
    logout: () -> Unit,
    navigateToAddMeasurement: (Int, Measurement?) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.effectFlow.collectLatest {
            when (it) {
                UiEffect.Logout -> logout()
                else -> {}
            }
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                Column(
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                ) {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    Text(
                        text = "Снегосъемка",
                        style = MaterialTheme.typography.displaySmall
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    Divider()

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    NavigationDrawerItem(
                        label = { Text(text = "Главная") },
                        selected = true,
                        onClick = {
                            navController.navigate(MainRoute.MainScreen.route) {
                                launchSingleTop = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(imageVector = Icons.Default.Straighten, contentDescription = null)
                        }
                    )
//                    NavigationDrawerItem(
//                        label = { Text(text = "Информация") },
//                        selected = false,
//                        onClick = {
//                            navController.navigate(MainRoute.InfoScreen.route) {
//                                launchSingleTop = true
//                            }
//                            scope.launch {
//                                drawerState.close()
//                            }
//                        },
//                        icon = {
//                            Icon(imageVector = Icons.Default.Info, contentDescription = null)
//                        }
//                    )

                    NavigationDrawerItem(
                        label = { Text(text = "Выйти") },
                        selected = false,
                        onClick = {
                            viewModel.onEvent(MainEvent.Logout)
                            scope.launch { drawerState.close() }
                        },
                        icon = {
                            Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                        }
                    )

                }
            }
        },
        drawerState = drawerState
    ) {
        NavHost(navController = navController, startDestination = mainRoute) {
            mainScreen(
                openDrawer = {
                    scope.launch { drawerState.open() }
                },
                navigateToAddMeasurement = navigateToAddMeasurement
            )

            composable(MainRoute.MainScreen.route) {
                ResultsRoute(
                    openDrawer = {
                        scope.launch { drawerState.open() }
                    },
                    navigate = navigate
                )
            }

            composable(MainRoute.InfoScreen.route) {
                Text(text = "info")
            }
        }
    }
}