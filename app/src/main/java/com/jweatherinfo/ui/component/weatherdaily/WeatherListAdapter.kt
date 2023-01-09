package com.jweatherinfo.ui.component.weatherdaily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jweatherinfo.android.databinding.ItemDailyWeatherBinding
import com.jweatherinfo.data.models.WeatherInfo

class WeatherListAdapter : RecyclerView.Adapter<WeatherInfoViewHolder>() {

    private var weatherList = mutableListOf<WeatherInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoViewHolder {
        val binding = ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        holder.onBind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    @Suppress("notifyDataSetChanged")
    fun setData(weatherList: List<WeatherInfo>) {
        if (this.weatherList.isNotEmpty()) {
            this.weatherList.clear()
        }
        this.weatherList = weatherList.toMutableList()
    }
}