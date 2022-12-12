package com.vpr.scheduleapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vpr.scheduleapp.ui.theme.ScheduleAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: MainViewModel by viewModels()
        vm.getScheduleByStationCodeAndDate(
            from = "s9879631", to = "s9600771", date = LocalDate.now().format(
                DateTimeFormatter.ISO_DATE
            )
        )

        //vm.getStationsFromApi()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            ScheduleAppTheme {
                ScheduleListScreen(snackbarHostState)

                LaunchedEffect(key1 = true) {
                    vm.errorMessageSharedFlow.collectLatest {
                        snackbarHostState.showSnackbar(message = it)
                    }
                }
            }
        }
    }

    //todo
    // -make navigation(?)
    // -make a new screen for whoever knows what lol (displaying train details or about app tab)
    // -implement stations searching
    // -implement different schedule for different stations, directions and dates
    // -therefore, make it possible to switch between dates
    // migrate to rxjava


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScheduleListScreen(snackbarHostState: SnackbarHostState ,modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
        Scaffold (snackbarHost = { SnackbarHost(snackbarHostState) }, content = { padding ->
            Surface(modifier= Modifier.padding(padding)) {
                val scheduleListState = viewModel.scheduleListState.value
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    val focusManager = LocalFocusManager.current
                    SearchBar(
                        onQueryChange = {
                            //viewModel.getScheduleByStationAndDate("s9600771", "2022-11-17")
                            //todo how to clear focus when not typing and clicked/swiped other element?
                        },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            viewModel.getScheduleByStationCodeAndDate(
                                from = "s9879631", to = "s9600771", date = LocalDate.now().format(
                                    DateTimeFormatter.ISO_DATE
                                )
                            )
                        }) {
                            Text(text = "Today", fontSize = 12.sp)
                        }
                        Button(onClick = {
                            viewModel.getScheduleByStationCodeAndDate(
                                from = "s9879631",
                                to = "s9600771",
                                date = LocalDate.now().plusDays(1).format(
                                    DateTimeFormatter.ISO_DATE
                                )
                            )
                        }) {
                            Text(text = "Tomorrow", fontSize = 12.sp)
                        }
                    }
                    LazyColumn(modifier.pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus()
                        })
                    }) {
                        items(scheduleListState.segment.size) { i ->
                            val scheduleElement = scheduleListState.segment[i]
                            scheduleElement?.let {
                                ScheduleSimpleCard(
                                    shortTitle = scheduleElement.thread.short_title
                                        ?: scheduleElement.thread.title
                                        ?: "Unknown station",
                                    departureTime = scheduleElement.departure,
                                    arrivalTime = scheduleElement.arrival,
                                    travelTime = scheduleElement.duration.toString()
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (scheduleListState.isLoading) {
                        CircularProgressIndicator()
                    }
                }

            }
        }
        )
    }
}


