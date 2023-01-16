package com.jweatherinfo.ui.cityworld

import android.view.LayoutInflater
import com.happyfresh.happyarch.ComponentProvider
import com.happyfresh.happyarch.Subscribe
import com.jweatherinfo.android.databinding.FragmentCitiesBinding
import com.jweatherinfo.core.ui.BaseFragment
import com.jweatherinfo.data.event.OnClick
import com.jweatherinfo.data.event.OnSubmit
import com.jweatherinfo.data.local.dao.FavoriteCity
import com.jweatherinfo.data.models.WeatherInfo
import com.jweatherinfo.ui.component.favorite.FavoriteCityComponent
import com.jweatherinfo.ui.component.search.SearchCitiesComponent
import com.jweatherinfo.ui.component.weather.WeatherDetailsComponent
import com.jweatherinfo.ui.component.weatherdaily.WeatherListComponent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CitiesFragment : BaseFragment<FragmentCitiesBinding>() {

    @Inject
    lateinit var viewModel: CitiesViewModel

    override fun initViewBinding(inflater: LayoutInflater): FragmentCitiesBinding {
        return FragmentCitiesBinding.inflate(inflater)
    }

    override fun onFragmentInitiated() {
        viewModel.loadFavoriteCities(eventObservable)
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
            add(
                WeatherListComponent::class.java,
                binding?.componentWeatherListCityContainer?.root
            )
            add(
                FavoriteCityComponent::class.java,
                binding?.componentFavoriteCityContainer?.root
            )
        }
    }

    @Subscribe(SearchCitiesComponent::class)
    fun subscribeSearchQueries(onSubmit: OnSubmit<String>) {
        viewModel.fetchCityWeather(eventObservable, onSubmit.item)
    }

    @Subscribe(WeatherDetailsComponent::class)
    fun subscribeOnClickAsFavorite(onClick: OnClick<WeatherInfo>) {
        viewModel.saveAsFavoriteCity(eventObservable, onClick.item)
    }

    @Subscribe(FavoriteCityComponent::class)
    fun subscribeOnFavoriteClick(onClick: OnClick<FavoriteCity>) {
        viewModel.fetchCityWeather(eventObservable, onClick.item.name)
    }

}