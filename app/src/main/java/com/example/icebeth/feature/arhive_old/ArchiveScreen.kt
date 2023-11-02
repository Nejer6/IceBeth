//package com.example.icebeth.feature.arhive_old
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.icebeth.common.presentation.theme.spacing
//import com.example.icebeth.common.util.formatDateFromTimestamp
//import com.example.icebeth.core.model.InactiveResult
//import com.example.icebeth.common.presentation.components.MyCard
//
//@Composable
//fun ArchiveRoute(
//    openDrawer: () -> Unit,
//    viewModel: ArchiveViewModel = hiltViewModel()
//) {
//
//    val inactiveResults by viewModel.inactiveResultsFlow
//        .collectAsState(
//            initial = emptyList()
//        )
//
//    ArchiveScreen(
//        openDrawer,
//        inactiveResults
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ArchiveScreen(
//    openDrawer: () -> Unit,
//    inactiveResults: List<InactiveResult>
//) {
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Архив") },
//                navigationIcon = {
//                    IconButton(onClick = openDrawer) {
//                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Меню")
//                    }
//                },
//                scrollBehavior = scrollBehavior
//            )
//        },
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
//    ) { paddingValues ->
//        LazyColumn(
//            modifier =
//            Modifier
//                .padding(paddingValues)
//                .fillMaxWidth()
//                .padding(horizontal = MaterialTheme.spacing.small),
//            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
//        ) {
//            if (inactiveResults.isEmpty()) {
//                item {
//                    Text(text = "Вы еще не сохраняли измерения.")
//                }
//            } else {
//                items(inactiveResults) {
//                    MyCard(
//                        title = "Измерение (${it.time.formatDateFromTimestamp()})",
//                        statsWithTitles = listOf(
//                            "Высота снега" to listOf(
//                                "Средняя" to it.averageSnowHeight,
//                                "Максимальная" to it.maxSnowHeight,
//                                "Минимальная" to it.minSnowHeight,
//                            )
//                        )
//                    )
//                }
//
//                item {
//                    Spacer(
//                        modifier = Modifier.height(
//                            MaterialTheme.spacing.extraLarge +
//                                    MaterialTheme.spacing.medium
//                        )
//                    )
//                }
//            }
//        }
//    }
//}