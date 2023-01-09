package com.jweatherinfo.data.remote.response

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lng: Double,
    @SerializedName("country")
    val country: String
)
