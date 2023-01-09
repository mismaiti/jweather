package com.jweatherinfo.ui.component.weatherdaily

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.android.databinding.ComponentWeatherListBinding
import com.jweatherinfo.core.ext.visible
import com.jweatherinfo.core.ui.BaseUiView
import com.jweatherinfo.data.models.WeatherInfo

class WeatherListUiView(view: View, eventObservable: EventObservable) :
    BaseUiView<ComponentWeatherListBinding>(
        view, eventObservable,
        { ComponentWeatherListBinding.bind(view) }) {

    private val adapter: WeatherListAdapter by lazy { WeatherListAdapter() }

    init {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding?.run {
            val layoutManager = LinearLayoutManager(context)
            with(weatherDailyRv) {
                this.layoutManager = layoutManager
                this.adapter = this@WeatherListUiView.adapter
            }
        }
    }

    fun loadDailyList(weatherList: List<WeatherInfo>) {
        binding?.titleWeatherForecastTv?.visible()
        adapter.setData(weatherList)
        adapter.notifyDataSetChanged()
    }
}