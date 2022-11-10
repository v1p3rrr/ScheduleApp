package com.vpr.scheduleapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vpr.scheduleapp.ui.theme.ScheduleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: MainViewModel by viewModels()
        vm.getSchedule()
        vm.getStationsFromApi()
        setContent {
            ScheduleAppTheme {
                ScheduleListScreen()
            }
        }
    }

    //todo
    // -searchView
    // -travelTime difference
    // -storing entities properly with foreign keys
    // -migrate to fragments(?)
    // -make navigation(?)
    // -make a new screen for whoever knows what lol
    // -make stations searching
    // -implement different schedule for different stations and different _days_
    // -therefore, make it possible to switch between dates

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
    Surface {
        val scheduleList = viewModel.scheduleLiveData.observeAsState()
        LazyColumn(modifier) {
            if (scheduleList.value != null) {
                for (i in scheduleList.value!!.schedule) {  //todo nullable!!
                    item {
                        ScheduleSimpleCard(
                            shortTitle = i.thread.short_title,
                            departureTime = i.departure,
                            arrivalTime = i.arrival,
                            travelTime = "none" //todo
                        )
                    }
                }
            }
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
        modifier.padding(horizontal = 8.dp, vertical = 2.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(modifier = Modifier.padding(horizontal = 32.dp), text = departureTime)
                Text(modifier = Modifier.padding(horizontal = 32.dp), text = arrivalTime)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier, text = shortTitle, textAlign = TextAlign.Center)
            }
        }
    }
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
