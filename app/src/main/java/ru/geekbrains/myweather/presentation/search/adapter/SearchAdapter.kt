package ru.geekbrains.myweather.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.R.layout.item_search

class SearchAdapter(private val clickCityDelegat: ClickCityDelegat): ListAdapter<City, SearchViewHolder>(ItemCityDiff) {

    interface ClickCityDelegat{
        fun clickCity(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(item_search, parent, false)
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(getItem(position), clickCityDelegat)

}

object ItemCityDiff: DiffUtil.ItemCallback<City>(){
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

}