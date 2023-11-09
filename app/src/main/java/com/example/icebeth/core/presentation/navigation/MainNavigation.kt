package com.example.icebeth.core.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.feature.archive.navigation.archiveRoute
import com.example.icebeth.feature.archive.navigation.archiveScreen
import com.example.icebeth.feature.main.navigation.mainRoute
import com.example.icebeth.feature.main.navigation.mainScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val mainGraph = "main_graph"

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    navigate(mainGraph, navOptions)
}

fun NavGraphBuilder.mainGraph(
    logout: () -> Unit,
    navigateToActiveResult: () -> Unit
) {
    composable(mainGraph) {
        MainNavigation(
            logout = logout,
            navigateToActiveResult = navigateToActiveResult
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    logout: () -> Unit,
    navigateToActiveResult: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navigationDrawerItems = listOf(
        NavigationDrawerData(
            "Снегосъемка",
            onClick = {
                navController.navigate(mainRoute) {
                    launchSingleTop = true
                    popUpTo(mainRoute) {
                        inclusive = true
                    }
                }
            },
            Icons.Default.Straighten,
            mainRoute
        ),
        NavigationDrawerData(
            "Архив",
            onClick = {
                navController.navigate(archiveRoute) {
                    launchSingleTop = true
                    popUpTo(mainRoute)
                }
            },
            Icons.Default.Archive,
            archiveRoute
        ),
        NavigationDrawerData(
            "Выйти",
            onClick = {
                viewModel.onEvent(MainEvent.Logout)
            },
            Icons.Default.Logout,
            null
        )
    )

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
                        text = "Главная",
                        style = MaterialTheme.typography.displaySmall
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    Divider()

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    navigationDrawerItems.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(text = item.label) },
                            selected = (item.route != null) && (item.route == currentRoute),
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                                item.onClick()
                            },
                            icon = {
                                Icon(imageVector = item.icon, contentDescription = null)
                            }
                        )
                    }
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
                navigateToActiveResult = navigateToActiveResult
            )

            archiveScreen(
                openDrawer = {
                    scope.launch { drawerState.open() }
                }
            )
        }
    }
}

data class NavigationDrawerData(
    val label: String,
    val onClick: () -> Unit,
    val icon: ImageVector,
    val route: String?
)
