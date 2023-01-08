package com.jweatherinfo.data.models

import java.util.*

data class WeatherInfo(
    val date: Date,
    val city: String,
    val temperature: Double,
    val weather: String,
    val humidity: Double,
    val wind: Double,
    val isFavorite: Boolean
) {
    companion object {
        val mockSingle = WeatherInfo(
            date = Calendar.getInstance().time,
            city = "Jakarta",
            temperature = 24.5,
            weather = "Shiny cloud",
            humidity = 54.0,
            wind = 20.5,
            isFavorite = false
        )
        val mockList = listOf(
            mockSingle, mockSingle, mockSingle
        )
    }
}
