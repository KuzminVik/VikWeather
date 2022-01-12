package ru.geekbrains.myweather.presentation.currentweather

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.geekbrains.myweather.databinding.FragmentCurrentWeatherBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.R
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_current_weather
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.HourEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity
import ru.geekbrains.myweather.presentation.arguments
import ru.geekbrains.myweather.presentation.currentweather.adapter.CurrentAdapter
import ru.geekbrains.myweather.presentation.currentweather.adapter.HourlyAdapter
import ru.geekbrains.myweather.presentation.setIconWithGlide
import ru.geekbrains.myweather.presentation.showDialogWithInformation
import ru.geekbrains.myweather.util.ARGUMENT_USER
import ru.geekbrains.myweather.util.TAG_CURRENT
import java.time.Instant.ofEpochSecond
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject

class CurrentWeatherFragment : AbsFragment(fragment_current_weather), CurrentWeatherView {

    private val viewBinding: FragmentCurrentWeatherBinding by viewBinding()

    private val nameCity: String by lazy {
        arguments?.getString(ARG_USER).orEmpty()
    }

    private val currentAdapter = CurrentAdapter()
    private val hourlyAdapter = HourlyAdapter()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var repo: WeatherRepository

    private val presenter: CurrentWeatherPresenter by moxyPresenter {
        CurrentWeatherPresenter(
            nameCity = nameCity,
            repo = repo,
            router = router,
            schedulers = schedulers,
            sharedPreferences = sharedPreferences
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            rwDaily.adapter = currentAdapter
            rwDaily.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvHourly.adapter = hourlyAdapter
        }
        showProgressBar()
    }

    @SuppressLint("SetTextI18n")
    override fun showCurrentWeather(weatherEntity: WeatherEntity) {
        val percent = getString(R.string.percent)
        with(viewBinding){
            tvData.text = convertUnixTime(weatherEntity.dt)
            tvCity.text = weatherEntity.name
            tvTemp.text = getString(R.string.temp, weatherEntity.temp)
            tvTempMorn.text = getString(R.string.temp_morn, weatherEntity.tempMorn)
            tvTempDay.text = getString(R.string.temp_day, weatherEntity.tempDay)
            tvTempEve.text = getString(R.string.temp_eve, weatherEntity.tempEve)
            tvTempNight.text = getString(R.string.temp_night, weatherEntity.tempNight)
            tvTempFeel.text = getString(R.string.temp_feel, weatherEntity.feelsLike)
            tvDesc.text = weatherEntity.description
            tvPressure.text = getString(R.string.pressure, weatherEntity.pressure)
            tvHumidity.text = getString(R.string.humidity, weatherEntity.humidity, percent)
            tvDewPoint.text = getString(R.string.dew_point, weatherEntity.dewPoint)
            tvCloud.text = getString(R.string.cloud_cover, weatherEntity.clouds, percent)
            tvVisibility.text = getString(R.string.visibility, weatherEntity.visibility)
            tvWind.text = getString(R.string.wind, weatherEntity.windSpeed)
            ivIcon.setIconWithGlide(weatherEntity.icon)
            hideProgressBar()
        }
    }

    private fun hideProgressBar(){
        with(viewBinding){
            constraintlayoutTop.visibility = View.VISIBLE
            constraintlayoutCenter.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun showProgressBar(){
        with(viewBinding){
            constraintlayoutTop.visibility = View.GONE
            constraintlayoutCenter.visibility = View.GONE
            tvLabelDaily.visibility = View.GONE
            tvLabelHourly.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun showErrorCurrentWeather(e: Throwable?) {
        showDialogWithInformation(getString(R.string.msg_no_data), getString(R.string.error))
        e?.message?.let {
            Log.d(TAG, it)
        }
    }

    override fun showDaily(list: List<DayEntity>) {
        viewBinding.tvLabelDaily.visibility = View.VISIBLE
        currentAdapter.submitList(list)
    }

    override fun showHourly(data: List<Pair<HourEntity, Boolean>>) {
        viewBinding.tvLabelHourly.visibility = View.VISIBLE
        hourlyAdapter.setData(data)
    }

    private fun convertUnixTime(unixTime: Long): String{
        val dateTimeInstant = ofEpochSecond(unixTime)
        val dateLocal = dateTimeInstant.atZone(ZoneId.systemDefault()).toLocalDateTime()
        return dateLocal.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT))
    }

    override fun setTitle(title: String) {
        actionBar!!.title = title
    }

    companion object {
        private const val TAG = TAG_CURRENT
        private const val ARG_USER: String = ARGUMENT_USER
        fun newInstance(nameCity: String): Fragment = CurrentWeatherFragment().arguments(ARG_USER to nameCity)
    }
}