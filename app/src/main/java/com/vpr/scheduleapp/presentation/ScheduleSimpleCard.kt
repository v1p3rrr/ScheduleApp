package com.vpr.scheduleapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
                Text(modifier = Modifier.padding(start = 16.dp), text = departureTime)
                Text(modifier = Modifier.padding(horizontal = 4.dp), text = travelTime)
                Text(modifier = Modifier.padding(end = 16.dp), text = arrivalTime)
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
