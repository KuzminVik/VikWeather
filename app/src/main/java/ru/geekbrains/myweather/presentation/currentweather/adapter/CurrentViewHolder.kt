package ru.geekbrains.myweather.presentation.currentweather.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.geekbrains.myweather.R
import ru.geekbrains.myweather.databinding.ItemDailyBinding
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.presentation.setIconWithGlide
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class CurrentViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val viewBinding: ItemDailyBinding by viewBinding()

    fun bind(day: DayEntity) {
        with(viewBinding) {
            tvDate.text = convertUnixTime(day.dt)
            tvTempDay.text = itemView.context.resources.getString(R.string.temp_day, day.tempDay)
            tvTempNight.text = itemView.context.resources.getString(R.string.temp_night, day.tempNight)
            ivIcon.setIconWithGlide(day.icon)
        }
    }

    private fun convertUnixTime(unixTime: Long): String{
        val dateTimeInstant = Instant.ofEpochSecond(unixTime)
        val dateLocal = dateTimeInstant.atZone(ZoneId.systemDefault()).toLocalDateTime()
        return dateLocal.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
    }
}