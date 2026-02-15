package com.example.app1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app1.viewmodel.WeatherUiState
import com.example.app1.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val city by viewModel.city.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = city,
            onValueChange = { viewModel.updateCity(it) },
            label = { Text("Syötä kaupunki") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.fetchWeather() }) {
            Text("Hae sää")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = uiState) {
            is WeatherUiState.Idle -> Text("Hae sää")
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Success -> {
                Text(
                    text = "${state.response.main.temp} °C",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = state.response.weather.firstOrNull()?.description ?: "",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            is WeatherUiState.Error -> {
                Text(text = state.message, color = Color.Red)
            }
        }
    }
}