package com.jweatherinfo.ui.component.weather

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.happyfresh.happyarch.EventObservable
import com.happyfresh.happyarch.Subscribe
import com.jweatherinfo.core.ui.BaseComponent
import com.jweatherinfo.data.event.Loaded
import com.jweatherinfo.data.event.Loading
import com.jweatherinfo.data.models.WeatherInfo

class WeatherDetailsComponent(view: View, lifecycleOwner: LifecycleOwner)
    : BaseComponent<WeatherDetailsUiView>(view, lifecycleOwner){

    override fun onCreateView(
        view: View,
        eventObservable: EventObservable
    ): WeatherDetailsUiView {
        return WeatherDetailsUiView(view, eventObservable)
    }

    @Subscribe(WeatherDetailsComponent::class)
    fun subscribeCurrentWeather(weatherInfo: Loaded<WeatherInfo>) {
        weatherInfo.data?.let {
            uiView.show()
            uiView.loadCurrentWeather(it)
        }?: run {
            uiView.hide()
        }
    }

    @Subscribe(WeatherDetailsComponent::class)
    fun subscribeLoadingState(loading: Loading) {
        uiView.showLoadingState()
    }
}