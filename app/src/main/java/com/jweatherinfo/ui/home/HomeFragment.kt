package com.jweatherinfo.ui.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.happyfresh.happyarch.ComponentProvider
import com.jweatherinfo.android.databinding.FragmentHomeBinding
import com.jweatherinfo.core.ui.BaseFragment
import com.jweatherinfo.data.event.Loaded
import com.jweatherinfo.data.event.WeatherListLoaded
import com.jweatherinfo.data.models.WeatherInfo
import com.jweatherinfo.ui.component.weather.WeatherDetailsComponent
import com.jweatherinfo.ui.component.weatherdaily.WeatherListComponent

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun initViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun bindComponent(provider: ComponentProvider) {
        with(provider) {
            add(
                WeatherDetailsComponent::class.java,
                binding?.componentCurrentWeatherContainer?.root
            )
            add(
                WeatherListComponent::class.java,
                binding?.componentDailyWeatherContainer?.root
            )

        }
    }

    override fun onFragmentInitiated() {
        eventObservable.emit(WeatherDetailsComponent::class.java, Loaded(WeatherInfo.mockSingle))
        eventObservable.emit(WeatherListComponent::class.java, WeatherListLoaded(WeatherInfo.mockList))
    }

}