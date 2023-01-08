package com.jweatherinfo.ui.component.search

import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.android.databinding.ComponentSearchCitiesBinding
import com.jweatherinfo.core.ui.BaseUiView
import com.jweatherinfo.data.event.Loading
import com.jweatherinfo.data.event.OnSubmit
import com.jweatherinfo.ui.component.weather.WeatherDetailsComponent

class SearchCitiesUiView(
    view: View,
    eventObservable: EventObservable
) : BaseUiView<ComponentSearchCitiesBinding>(
        view, eventObservable, { ComponentSearchCitiesBinding.bind(view) }) {

    fun setupSearchView() {
        binding?.run {
            searchCitiesSv.setOnQueryTextListener(object : OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isNotEmpty()) {
                        eventObservable.emit(SearchCitiesComponent::class.java, OnSubmit(query))
                        eventObservable.emit(WeatherDetailsComponent::class.java, Loading())
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }
}