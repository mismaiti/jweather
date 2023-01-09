package com.jweatherinfo.data.models

import com.jweatherinfo.data.remote.response.WeatherForecastResponse
import com.jweatherinfo.data.remote.response.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*

data class WeatherInfo(
    val date: Date,
    val city: String?,
    val temperature: Double,
    val weather: String,
    val humidity: Int,
    val wind: Double,
    var isFavorite: Boolean,
    var lat: Double,
    var lng: Double
) {
    companion object {
        val mockSingle = WeatherInfo(
            date = Calendar.getInstance().time,
            city = "Jakarta",
            temperature = 24.5,
            weather = "Shiny cloud",
            humidity = 54,
            wind = 20.5,
            isFavorite = false,
            lat = 0.0,
            lng = 0.0
        )
        val mockList = listOf(
            mockSingle, mockSingle, mockSingle
        )
    }
}

fun WeatherResponse.toWeatherInfo(): WeatherInfo {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
    return WeatherInfo(
        date = if (dateText.isNullOrEmpty().not()) {
            dateFormat.parse(dateText) ?: Calendar.getInstance().time
        } else {
            Calendar.getInstance().time
        },
        city = this.cityResponse?.name ?: this.city.orEmpty(),
        temperature = this.mainInfo.temperature,
        weather = String.format("%s (%s)", this.weather[0].mainWeather, this.weather[0].desc),
        humidity = this.mainInfo.humidity,
        wind = this.wind.speed,
        isFavorite = false,
        lat = this.cityResponse?.lat ?: 0.0,
        lng = this.cityResponse?.lng ?: 0.0
    )
}

fun WeatherForecastResponse.toWeatherInfoList(): List<WeatherInfo> {
    return weatherList.map {
        it.toWeatherInfo()
    }
}
