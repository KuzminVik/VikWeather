package ru.geekbrains.myweather.presentation.currentweather.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.myweather.databinding.ItemDailyBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.presentation.setDrawableFromUri

class CurrentViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val viewBinding: ItemDailyBinding by viewBinding()

    fun bind(day: DayEntity) {
        with(viewBinding) {
            tvDate.text = day.dt.toString()
            tvTempDay.text = day.tempDay.toString()
            tvTempNight.text = day.tempNight.toString()
            val uri: Uri = Uri.parse("http://openweathermap.org/img/wn/${day.icon}@2x.png")
            ivIcon.setDrawableFromUri(uri)
        }
    }
}