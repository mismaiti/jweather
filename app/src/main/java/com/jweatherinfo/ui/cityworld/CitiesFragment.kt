package com.jweatherinfo.ui.cityworld

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.happyfresh.happyarch.ComponentProvider
import com.happyfresh.happyarch.Subscribe
import com.jweatherinfo.android.databinding.FragmentCitiesBinding
import com.jweatherinfo.core.ui.BaseFragment
import com.jweatherinfo.data.event.Loaded
import com.jweatherinfo.data.event.OnSubmit
import com.jweatherinfo.data.models.WeatherInfo
import com.jweatherinfo.ui.component.search.SearchCitiesComponent
import com.jweatherinfo.ui.component.weather.WeatherDetailsComponent
import com.jweatherinfo.ui.component.weatherdaily.WeatherListComponent

class CitiesFragment : BaseFragment<FragmentCitiesBinding>() {

    private val viewModel: CitiesViewModel by viewModels()

    override fun initViewBinding(inflater: LayoutInflater): FragmentCitiesBinding {
        return FragmentCitiesBinding.inflate(inflater)
    }

    override fun onFragmentInitiated() {

    }

    override fun bindComponent(provider: ComponentProvider) {
        with(provider) {
            add(
                SearchCitiesComponent::class.java,
                binding?.componentSearchCitiesContainer?.root
            )
            add(
                WeatherDetailsComponent::class.java,
                binding?.componentWeatherDetailsCitiesContainer?.root
            )
        }
    }

    @Subscribe(SearchCitiesComponent::class)
    fun subscribeSearchQueries(onSubmit: OnSubmit<String>) {
        eventObservable.emit(WeatherDetailsComponent::class.java, Loaded(WeatherInfo.mockSingle))
    }


}