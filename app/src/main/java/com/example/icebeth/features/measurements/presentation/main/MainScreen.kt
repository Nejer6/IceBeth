package com.example.icebeth.features.measurements.presentation.main

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.example.icebeth.features.measurements.presentation.main.components.TextWithNumber
import com.example.icebeth.shared.presentation.theme.IceBethTheme
import com.example.icebeth.shared.presentation.theme.spacing
import com.example.icebeth.shared.presentation.util.AppRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainState,
    openDrawer: () -> Unit,
    navigate: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Все замеры") },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Меню")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigate(AppRoute.AddMeasurementScreen.route) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить измерение"
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            items(state.measurements.size) {
                var isCardOpen by remember {
                    mutableStateOf(false)
                }

                val rotation by animateFloatAsState(
                    targetValue = if (isCardOpen) 90f else 0f, label = ""
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    onClick = { isCardOpen = !isCardOpen }
                ) {
                    val measurement = state.measurements[it]

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.medium)
                    ) {
                        Text(
                            text = "Замер №${measurement.id}"
                        )

                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = null,
                            modifier = Modifier.graphicsLayer { rotationZ = rotation }
                        )
                    }

                    if (isCardOpen) {
                        Divider(
                            color = MaterialTheme.colorScheme.outline
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            Column(
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(MaterialTheme.spacing.medium)
                            ) {

                                TextWithNumber(text = "Масса снега", float = measurement.massOfSnow)
                                TextWithNumber(
                                    text = "Высота снега",
                                    float = measurement.snowHeight
                                )
                                TextWithNumber(
                                    text = "Высота цилиндра",
                                    float = measurement.cylinderHeight
                                )

                                if (measurement.snowCrust || measurement.groundFrozzed) {
                                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                                    if (measurement.snowCrust) Text(text = "Снежная корка")
                                    if (measurement.groundFrozzed) Text(text = "Земля промерзла")
                                }
                            }

                            Column(
                                modifier = Modifier.padding(end = MaterialTheme.spacing.extraSmall)
                            ) {
                                IconButton(onClick = {
                                    navigate(
                                        "${AppRoute.AddMeasurementScreen.route}?measurement=${
                                            Json.encodeToString(
                                                measurement
                                            )
                                        }"
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Редактировать"
                                    )
                                }

                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Удалить",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            }
        }
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    IceBethTheme {
        MainScreen(
            MainState(),
            {}
        ) {}
    }
}