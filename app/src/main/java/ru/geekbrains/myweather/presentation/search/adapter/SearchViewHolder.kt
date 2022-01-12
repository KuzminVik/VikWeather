package ru.geekbrains.myweather.presentation.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.myweather.databinding.ItemSearchBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.presentation.click

class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val viewBinding: ItemSearchBinding by viewBinding()

    fun bind(city: City, clickCityDelegat: SearchAdapter.ClickCityDelegat){
        viewBinding.tvNameCity.text = city.name
        viewBinding.root.click{
            clickCityDelegat.clickCity(city.name)
        }
    }

}