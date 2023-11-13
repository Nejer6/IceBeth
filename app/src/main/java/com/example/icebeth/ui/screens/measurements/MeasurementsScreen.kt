package com.example.icebeth.ui.screens.measurements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.icebeth.ui.theme.spacing
import com.example.icebeth.util.formatTimeFromTimestamp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MeasurementsScreen(
    navigateUp: () -> Unit,
    state: MeasurementsState
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Измерения")
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        val extendedMeasurements = remember(key1 = state.measurements) {
            state.measurements.filter { it.cylinderHeight != null }
        }

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            itemsIndexed(extendedMeasurements) { index, it ->
                Column(
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "№: ", fontWeight = FontWeight.Bold)
                        Text(text = (index + 1).toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Высота снега: ", fontWeight = FontWeight.Bold)
                        Text(text = it.snowHeight.toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Высота цилиндра: ", fontWeight = FontWeight.Bold)
                        Text(text = it.cylinderHeight.toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Масса снега: ", fontWeight = FontWeight.Bold)
                        Text(text = it.massOfSnow.toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Снежная корка: ", fontWeight = FontWeight.Bold)
                        Text(
                            text = if (it.snowCrust!!) {
                                "Есть"
                            } else {
                                "Нет"
                            }
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Толщина ледяной корки: ", fontWeight = FontWeight.Bold)
                        Text(text = it.iceCrustThickness.toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Толщина слоя насыщ. водой: ", fontWeight = FontWeight.Bold)
                        Text(text = it.snowLayerWaterSaturation.toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Толщина слоя талой воды: ", fontWeight = FontWeight.Bold)
                        Text(text = it.thawedWaterLayerThickness.toString())
                    }
                    Text(text = "Состояние поверхности почвы: ", fontWeight = FontWeight.Bold)
                    Text(text = it.soilSurfaceCondition!!.description)
                }
                Divider()
            }

            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            }

            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        text = "№",
                        modifier = Modifier.weight(3f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Время",
                        modifier = Modifier.weight(5f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Высота снега",
                        modifier = Modifier.weight(12f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

                Divider()
            }

            itemsIndexed(state.measurements) { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = (index + 1).toString(),
                        modifier = Modifier.weight(3f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = item.time.formatTimeFromTimestamp(),
                        modifier = Modifier.weight(5f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = item.snowHeight.toString(),
                        modifier = Modifier.weight(12f),
                        textAlign = TextAlign.Center
                    )
                }

                Divider()
            }

            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            }
        }
    }
}
