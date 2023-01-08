package com.jweatherinfo.ui.component.weatherdaily

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.happyfresh.happyarch.EventObservable
import com.happyfresh.happyarch.Subscribe
import com.jweatherinfo.core.ui.BaseComponent
import com.jweatherinfo.data.event.WeatherListLoaded

class WeatherListComponent(view: View, lifecycleOwner: LifecycleOwner): BaseComponent<WeatherListUiView>(view, lifecycleOwner) {

    override fun onCreateView(view: View, eventObservable: EventObservable): WeatherListUiView {
        return WeatherListUiView(view, eventObservable)
    }

    @Subscribe(WeatherListComponent::class)
    fun subscribeDailyWeather(listDailyWeather: WeatherListLoaded) {
        listDailyWeather.data?.let {
            uiView.show()
            uiView.loadDailyList(it)
        }?: run { uiView.hide() }
    }
}