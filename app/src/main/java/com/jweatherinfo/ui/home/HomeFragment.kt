package com.jweatherinfo.ui.home

import android.Manifest
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.happyfresh.happyarch.ComponentProvider
import com.happyfresh.happyarch.Subscribe
import com.jweatherinfo.android.databinding.FragmentHomeBinding
import com.jweatherinfo.core.ui.BaseFragment
import com.jweatherinfo.core.util.LocationUtil
import com.jweatherinfo.data.event.Loaded
import com.jweatherinfo.data.models.WeatherInfo
import com.jweatherinfo.ui.component.weather.WeatherDetailsComponent
import com.jweatherinfo.ui.component.weatherdaily.WeatherListComponent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var viewModel: HomeViewModel

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
        LocationUtil.permissionRequest(requireContext(), requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) {
            if (it) {
                lifecycleScope.launch {
                    viewModel.fetchWeather(eventObservable = eventObservable, isCurrentLocation = true)
                    binding?.pullToRefresh?.setOnRefreshListener {
                        binding?.pullToRefresh?.isRefreshing = true
                        viewModel.fetchWeather(eventObservable = eventObservable, isCurrentLocation = true)
                    }
                }
            }
        }
    }

    @Subscribe(WeatherDetailsComponent::class)
    fun subscribeWeatherDetails(loaded: Loaded<WeatherInfo>) {
        binding?.pullToRefresh?.isRefreshing = false
    }

}