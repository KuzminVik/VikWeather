package ru.geekbrains.myweather.presentation.currentweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.R.layout.item_daily

class CurrentAdapter: ListAdapter<DayEntity, CurrentViewHolder>(ItemDailyDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder =
        CurrentViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(item_daily, parent, false)
        )

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) =
        holder.bind(getItem(position))

}