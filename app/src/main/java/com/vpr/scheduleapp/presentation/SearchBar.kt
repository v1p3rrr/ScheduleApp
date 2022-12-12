package com.vpr.scheduleapp.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

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