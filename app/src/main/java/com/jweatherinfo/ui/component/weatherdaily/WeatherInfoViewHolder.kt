package com.jweatherinfo.ui.component.weatherdaily

import androidx.recyclerview.widget.RecyclerView
import com.jweatherinfo.android.R
import com.jweatherinfo.android.databinding.ItemDailyWeatherBinding
import com.jweatherinfo.data.models.WeatherInfo
import java.text.SimpleDateFormat
import java.util.*

class WeatherInfoViewHolder(private val binding: ItemDailyWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(weatherInfo: WeatherInfo) {
        with(binding) {
            var dateFormat = SimpleDateFormat("dd", Locale.getDefault())
            val stringDate = dateFormat.format(weatherInfo.date)
            dateTv.text = stringDate
            dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            val stringDay = dateFormat.format(weatherInfo.date)
            dayTv.text = stringDay
            dateFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
            val stringMonthYear = dateFormat.format(weatherInfo.date)
            monthAndYearTv.text = stringMonthYear

            weatherConditionTv.text =
                itemView.context.getString(
                    R.string.weather_condition_with_value,
                    weatherInfo.weather
                )

            temperatureConditionTv.text =
                itemView.context.getString(
                    R.string.temperature_with_value,
                    weatherInfo.temperature
                )

            humidityConditionTv.text = itemView.context.getString(
                R.string.humidity_and_wind_speed_with_value,
                weatherInfo.humidity,
                weatherInfo.wind
            )
        }

    }
}