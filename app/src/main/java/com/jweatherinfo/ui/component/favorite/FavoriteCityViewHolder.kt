package com.jweatherinfo.ui.component.favorite

import androidx.recyclerview.widget.RecyclerView
import com.jweatherinfo.android.databinding.ItemFavoriteCityBinding
import com.jweatherinfo.data.local.dao.FavoriteCity

class FavoriteCityViewHolder(val binding: ItemFavoriteCityBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(city: FavoriteCity, clickListener: (FavoriteCity) -> Unit) {
        with(binding) {
            favoriteCityTv.text = city.name
            root.setOnClickListener {
                clickListener.invoke(city)
            }
        }
    }
}