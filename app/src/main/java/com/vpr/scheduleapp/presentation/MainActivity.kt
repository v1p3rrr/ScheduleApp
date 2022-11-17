package com.vpr.scheduleapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vpr.scheduleapp.BuildConfig
import com.vpr.scheduleapp.ui.theme.ScheduleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: MainViewModel by viewModels()
        vm.getSchedule()
        //vm.getStationsFromApi()
        setContent {
            ScheduleAppTheme {
                ScheduleListScreen()
            }
        }
    }

    //todo
    // -travelTime difference
    // -make navigation(?)
    // -make a new screen for whoever knows what lol (displaying train details or about app tab)
    // -implement stations searching
    // -implement different schedule for different stations, directions and dates
    // -therefore, make it possible to switch between dates
    // migrate to rxjava

    fun someOldTestingShit() {
//        {
//                vm.stationsLiveData.observe(this) { it ->
//                binding.btnToday.text =  it.countries[it.countries.indexOfFirst {
//                    it.title == "Россия"
//                }].title
//
//                Log.d("API", it.countries.joinToString())
//            }
//        }
//    }
    }
}

@Composable
fun ScheduleListScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    Surface { //todo surface / scaffold diff, is containers amount overuse ok?
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            val scheduleList = viewModel.scheduleLiveData.observeAsState()
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
                    .padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Today", fontSize = 12.sp)
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Tomorrow", fontSize = 12.sp)
                }
            }
            LazyColumn(modifier.pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }) {
                if (scheduleList.value != null) {
                    for (i in scheduleList.value!!.schedule) {  //todo nullable!!
                        item {
                            ScheduleSimpleCard(
                                shortTitle = i.thread.short_title ?: i.thread.title
                                ?: "Unknown station",
                                departureTime = i.departure,
                                arrivalTime = i.arrival,
                                travelTime = i.travel_time
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun ScheduleSimpleCard(
    shortTitle: String,
    departureTime: String,
    arrivalTime: String,
    travelTime: String,
    //timeUntil: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .padding(horizontal = 8.dp, vertical = 2.dp)
            .clickable {
                //todo
            },
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(modifier = Modifier.padding(horizontal = 32.dp), text = departureTime)
                Text(modifier = Modifier.padding(horizontal = 32.dp), text = travelTime)
                Text(modifier = Modifier.padding(horizontal = 32.dp), text = arrivalTime)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier, text = shortTitle, textAlign = TextAlign.Center)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) //todo should i use some alternative??
@Composable
fun SearchBar(onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var queryState by remember { mutableStateOf("") }
    OutlinedTextField(
        value = queryState,
        label = {
            Text("Поиск")
        },
        onValueChange = {
            queryState = it
            onQueryChange(queryState)
        },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        modifier = modifier.fillMaxWidth(0.9f)
    )
}

@Preview
@Composable
fun PreviewScheduleSimpleCard() {
    ScheduleSimpleCard(
        shortTitle = "Москва - Питер",
        departureTime = "99:99",
        arrivalTime = "99:99",
        travelTime = "99:99"
    )
}
