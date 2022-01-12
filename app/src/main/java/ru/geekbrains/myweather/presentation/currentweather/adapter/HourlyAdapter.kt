package ru.geekbrains.myweather.presentation.currentweather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.myweather.R
import ru.geekbrains.myweather.databinding.ItemHourlyBinding
import ru.geekbrains.myweather.date.weather.models.HourEntity
import ru.geekbrains.myweather.presentation.setIconWithGlide
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class HourlyAdapter(): RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    private var values: MutableList<Pair<HourEntity, Boolean>> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Pair<HourEntity, Boolean>>){
        values = data.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyAdapter.ViewHolder {
        val binding = ItemHourlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyAdapter.ViewHolder, position: Int) = holder.bind(values[position])

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ItemHourlyBinding): RecyclerView.ViewHolder(binding.root){
        val percent = itemView.context.resources.getString(R.string.percent)
        fun bind(data: Pair<HourEntity, Boolean>){
            if (layoutPosition != RecyclerView.NO_POSITION){
                with(binding){
                    tvTime.text = convertUnixTime(data.first.dt)
                    tvTempHour.text = itemView.context.resources.getString(R.string.temp, data.first.temp)
                    tvDescIcon.text = data.first.description
                    tvDescHour.text = itemView.context.resources.getString(
                        R.string.desc_hourly_expand,
                        data.first.humidity, data.first.pressure, data.first.clouds, data.first.visibility, data.first.windSpeed, percent)
                    tvDescHour.visibility =
                        if(data.second) View.VISIBLE else View.GONE
                    ivIconToggle.setOnClickListener { toggleText() }
                    when (data.second){
                        true -> ivIconToggle.setImageResource(R.drawable.ic_baseline_expand_more_24)
                        false -> ivIconToggle.setImageResource(R.drawable.ic_baseline_expand_less_24)
                    }
                    ivIconWeatherHour.setIconWithGlide(data.first.icon)
                }
            }
        }

        private fun toggleText() {
            values[layoutPosition] = values[layoutPosition].let {
                it.first to !it.second
            }
            notifyItemChanged(layoutPosition)
        }
    }

    private fun convertUnixTime(unixTime: Long): String{
        val dateTimeInstant = Instant.ofEpochSecond(unixTime)
        val dateLocal = dateTimeInstant.atZone(ZoneId.systemDefault()).toLocalDateTime()
        return dateLocal.format(DateTimeFormatter.ofPattern( "D/MM H:mm" ))
    }
}