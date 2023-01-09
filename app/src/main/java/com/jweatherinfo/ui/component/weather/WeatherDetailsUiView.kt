package com.jweatherinfo.ui.component.weather

import android.view.View
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.android.databinding.ComponentWeatherDetailsBinding
import com.jweatherinfo.core.ext.gone
import com.jweatherinfo.core.ext.visible
import com.jweatherinfo.core.ui.BaseUiView
import com.jweatherinfo.data.models.WeatherInfo
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailsUiView(view: View, eventObservable: EventObservable) :
    BaseUiView<ComponentWeatherDetailsBinding>(view, eventObservable,
        { ComponentWeatherDetailsBinding.bind(view) }) {

    fun showLoadingState() {
        binding?.apply {
            weatherDetailsGroup.gone()
            weatherDetailProgressBar.visible()
        }
    }

    fun loadCurrentWeather(weatherInfo: WeatherInfo) {
        binding?.apply {
            weatherDetailsGroup.visible()
            weatherDetailProgressBar.gone()

            with(weatherInfo) {
                val cityString = StringBuilder(city.orEmpty())
                val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                cityString.append(" ")
                cityString.append(dateFormat.format(date))
                cityTv.text = cityString.toString()
                temperatureTv.text = temperature.toString()
                weatherTv.text = weather
                humidityValueTv.text = humidity.toString()
                windValueTv.text = wind.toString()
            }
        }
    }
}