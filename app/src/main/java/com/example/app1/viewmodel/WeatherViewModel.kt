package com.example.app1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app1.BuildConfig
import com.example.app1.data.model.WeatherResponse
import com.example.app1.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface WeatherUiState {
    object Idle : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val response: WeatherResponse) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}

class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState


    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city


    fun updateCity(newCity: String) {
        _city.value = newCity
    }

    fun fetchWeather() {
        val currentCity = _city.value
        if (currentCity.isBlank()) return

        _uiState.value = WeatherUiState.Loading

        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.getWeather(currentCity, BuildConfig.API_KEY)
                _uiState.value = WeatherUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error("Ei löydy")
            }
        }
    }
}