package com.jweatherinfo.ui.component.weatherdaily

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.android.R
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
                this.itemAnimator = DefaultItemAnimator()
                addItemDecoration(object : ItemDecoration(){
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        super.getItemOffsets(outRect, view, parent, state)
//                        if (parent.getChildAdapterPosition(view) == parent.childCount) {
////                            outRect.top = bindDimens(R.dimen.jweather_content_padding_small)
//                            outRect.bottom = bindDimens(R.dimen.jweather_content_padding_extra_large)
//                        }
//                        else {
//                        }
                        outRect.left = bindDimens(R.dimen.jweather_content_padding_small)
                        outRect.right = bindDimens(R.dimen.jweather_content_padding_small)
                    }
                })
            }
        }
    }

    @Suppress("notifyDataSetChanged")
    fun loadDailyList(weatherList: List<WeatherInfo>) {
        binding?.titleWeatherForecastTv?.visible()
        adapter.setData(weatherList)
        adapter.notifyDataSetChanged()
    }
}