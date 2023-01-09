package com.jweatherinfo.data.local.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jweatherinfo.data.models.WeatherInfo

@Entity(tableName = "favorite_city")
data class FavoriteCity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "lng")
    val lng: Double
)

fun WeatherInfo.toFavoriteCity(): FavoriteCity {
    return FavoriteCity(name = this.city.orEmpty(), lat = this.lat, lng = this.lng)
}
