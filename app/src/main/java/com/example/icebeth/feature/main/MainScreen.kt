package com.example.icebeth.feature.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.common.util.average
import com.example.icebeth.common.util.formatDateWithTimeFromTimestamp
import com.example.icebeth.core.data.util.ConnectivityObserver
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.core.model.ResultWithMeasurements
import com.example.icebeth.feature.main.components.MeasurementCard
import com.example.icebeth.features.measurements.presentation.components.TextWithNumber

@Composable
fun MainRoute(
    openDrawer: () -> Unit,
    navigateToAddMeasurement: (Int, Measurement?) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val resultWithMeasurements by viewModel.resultWithMeasurements.collectAsState(initial = null)
    val networkStatus by viewModel.networkStatusFlow.collectAsState(initial = ConnectivityObserver.Status.Unavailable)

    MainScreen(
        openDrawer = openDrawer,
        onStartMeasuring = viewModel::startMeasuring,
        resultWithMeasurements = resultWithMeasurements,
        deleteResult = viewModel::deleteResult,
        navigateToAddMeasurement = navigateToAddMeasurement,
        onDeleteMeasurement = viewModel::deleteMeasurement,
        onSaveResult = viewModel::saveResult,
        networkStatus = networkStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    openDrawer: () -> Unit,
    onStartMeasuring: () -> Unit,
    resultWithMeasurements: ResultWithMeasurements?,
    deleteResult: () -> Unit,
    navigateToAddMeasurement: (Int, Measurement?) -> Unit,
    onDeleteMeasurement: (Int) -> Unit,
    onSaveResult: () -> Unit,
    networkStatus: ConnectivityObserver.Status
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (resultWithMeasurements == null) {
                            "Снегосъемка"
                        } else {
                            "Съемка (${formatDateWithTimeFromTimestamp(resultWithMeasurements.result.time)})"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Меню")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            if (resultWithMeasurements == null) {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Начать съемку") },
                    icon = { Icon(imageVector = Icons.Default.Start, contentDescription = null) },
                    onClick = onStartMeasuring
                )
            } else {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Добавить замер") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Добавить измерение"
                        )
                    },
                    onClick = {
                        navigateToAddMeasurement(
                            resultWithMeasurements.result.id,
                            null
                        )
                    })
            }
        },
        bottomBar = {
            if (resultWithMeasurements != null) {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = deleteResult) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Удалить съемку"
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = onSaveResult) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = "Сохранить")
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            if (resultWithMeasurements != null) {
                item {
                    if (resultWithMeasurements.measurements.isNotEmpty()) {
                        Column(
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                        ) {
                            Text(
                                text = "Высота снега",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )

                            Column {
                                TextWithNumber(
                                    text = "Средняя",
                                    float = resultWithMeasurements.measurements.average {
                                        it.snowHeight
                                    }
                                )
                                TextWithNumber(
                                    text = "Максимальная",
                                    float = resultWithMeasurements.measurements.maxOf { it.snowHeight }
                                )
                                TextWithNumber(
                                    text = "Минимальная",
                                    float = resultWithMeasurements.measurements.minOf { it.snowHeight }
                                )
                            }


                        }
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                        Divider()
                    }
                }

                itemsIndexed(resultWithMeasurements.measurements, key = { _, measurement ->
                    measurement.id
                }) { index, measurement ->
                    MeasurementCard(
                        index = index + 1,
                        item = measurement,
                        navigate = navigateToAddMeasurement,
                        onDelete = onDeleteMeasurement
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(
                        MaterialTheme.spacing.extraLarge +
                        MaterialTheme.spacing.medium
                    ))
                }
            }
        }
    }
}