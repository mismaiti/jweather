package com.jweatherinfo.ui.component.favorite

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.happyfresh.happyarch.EventObservable
import com.jweatherinfo.android.R
import com.jweatherinfo.android.databinding.ComponentFavoriteCityBinding
import com.jweatherinfo.core.ui.BaseUiView
import com.jweatherinfo.data.event.OnClick
import com.jweatherinfo.data.local.dao.FavoriteCity

class FavoriteCityUiView(
    view: View, eventObservable: EventObservable
) : BaseUiView<ComponentFavoriteCityBinding>(
    view, eventObservable,
    { ComponentFavoriteCityBinding.bind(view) }) {

    private val adapter: FavoriteCityAdapter by lazy { FavoriteCityAdapter() }

    init {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding?.run {
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            with(favoriteCityRv) {
                this.layoutManager = layoutManager
                this.adapter = this@FavoriteCityUiView.adapter
                addItemDecoration(object: RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        super.getItemOffsets(outRect, view, parent, state)
                        if (parent.getChildAdapterPosition(view) == 0) {
                            outRect.left = bindDimens(R.dimen.jweather_content_padding_medium)
                        } else {
                            outRect.left = bindDimens(R.dimen.jweather_content_padding_small)
                        }

                        outRect.top = bindDimens(R.dimen.jweather_content_padding_small)
                        outRect.bottom = bindDimens(R.dimen.jweather_content_padding_small)

                    }
                })
            }
        }
    }

    fun loadData(list: List<FavoriteCity>) {
        adapter.setData(list) {
            eventObservable.emit(FavoriteCityComponent::class.java, OnClick(it))
        }
        adapter.notifyDataSetChanged()
    }
}