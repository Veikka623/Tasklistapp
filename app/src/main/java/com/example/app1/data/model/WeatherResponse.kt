package com.example.app1.data.model

data class WeatherResponse(
    val main: MainData,
    val weather: List<WeatherData>
)

data class MainData(
    val temp: Double
)

data class WeatherData(
    val description: String
)