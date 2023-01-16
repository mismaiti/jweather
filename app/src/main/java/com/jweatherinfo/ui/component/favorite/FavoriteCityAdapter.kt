package com.jweatherinfo.ui.component.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jweatherinfo.android.databinding.ItemFavoriteCityBinding
import com.jweatherinfo.data.local.dao.FavoriteCity

class FavoriteCityAdapter : RecyclerView.Adapter<FavoriteCityViewHolder>() {

    private var listFavoriteCity = listOf<FavoriteCity>()

    private var clickListener: (FavoriteCity) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCityViewHolder {
        val binding = ItemFavoriteCityBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteCityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCityViewHolder, position: Int) {
        holder.onBind(listFavoriteCity[position], clickListener)
    }

    override fun getItemCount(): Int {
        return listFavoriteCity.size
    }

    fun setData(list: List<FavoriteCity>, listener: (FavoriteCity) -> Unit) {
        listFavoriteCity = list
        clickListener = listener
    }
}