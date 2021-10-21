package ru.geekbrains.myweather.presentation.currentweather.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.geekbrains.myweather.date.weather.models.DayEntity

object ItemDailyDiff : DiffUtil.ItemCallback<DayEntity>() {

    private val payload = Any()
    override fun getChangePayload(oldItem: DayEntity, newItem: DayEntity) = payload

    override fun areItemsTheSame(oldItem: DayEntity, newItem: DayEntity): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DayEntity, newItem: DayEntity): Boolean {
        return oldItem == newItem
    }

}