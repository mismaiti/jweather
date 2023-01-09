package com.jweatherinfo.data.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("list")
    var weatherList: List<WeatherResponse>
)