package com.jweatherinfo.data.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main")
    val mainInfo: MainInfo,

    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("wind")
    val wind: Wind,

    @SerializedName("dt")
    val dateLong: Long,

    @SerializedName("dt_txt")
    val dateText: String,

    @SerializedName("name")
    val city: String?,

    var cityResponse: CitiesResponse? = null
)

data class MainInfo(
    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("feels_like")
    val feelsLike: Double
)

data class Weather(
    @SerializedName("main")
    val mainWeather: String,

    @SerializedName("description")
    val desc: String
)

data class Wind(
    @SerializedName("speed")
    val speed: Double
)
