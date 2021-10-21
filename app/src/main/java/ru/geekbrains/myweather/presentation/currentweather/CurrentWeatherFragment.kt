package ru.geekbrains.myweather.presentation.currentweather

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.geekbrains.myweather.databinding.FragmentCurrentWeatherBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_current_weather
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity
import ru.geekbrains.myweather.presentation.arguments
import ru.geekbrains.myweather.presentation.currentweather.adapter.CurrentAdapter
import ru.geekbrains.myweather.presentation.setDrawableFromUri
import javax.inject.Inject

class CurrentWeatherFragment : AbsFragment(fragment_current_weather), CurrentWeatherView {

    private val viewBinding: FragmentCurrentWeatherBinding by viewBinding()

    companion object {
        private const val TAG = "CurrentWeatherFragment"
        private const val ARG_USER = "ARG_NAME_CITY"
        fun newInstance(nameCity: String): Fragment = CurrentWeatherFragment().arguments(ARG_USER to nameCity)
    }

    private val nameCity: String by lazy {
        arguments?.getString(ARG_USER).orEmpty()
    }

    private val currentAdapter = CurrentAdapter()

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
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showCurrentWeather(weatherEntity: WeatherEntity) {
        with(viewBinding){
            tvData.text = convertUnixTime(weatherEntity.dt)
            tvCity.text = weatherEntity.name
            tvTemp.text = "${weatherEntity.temp} °C"
            tvTempFeel.text = "Ощущается как ${weatherEntity.feelsLike} °C"
            tvDesc.text = weatherEntity.description
            tvPressure.text = weatherEntity.pressure.toString()
            tvHumidity.text = weatherEntity.humidity.toString()
            tvDewPoint.text = weatherEntity.dewPoint.toString()
            tvCloud.text = weatherEntity.clouds.toString()
            tvVisibility.text = weatherEntity.visibility.toString()
            tvWind.text = weatherEntity.windSpeed.toString()
            val uri: Uri = Uri.parse("http://openweathermap.org/img/wn/${weatherEntity.icon}@2x.png")
            ivIcon.setDrawableFromUri(uri)
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

    override fun showErrorCurrentWeather(e: Throwable?) {
        e?.message?.let {
            Log.d(TAG, it)
            Toast.makeText(requireContext(),"ERROR $TAG: $it", Toast.LENGTH_LONG).show()
        }
    }

    override fun showDaily(list: List<DayEntity>) {
        currentAdapter.submitList(list)
    }

    private fun convertUnixTime(unixTime: Long): String{
        return java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(unixTime))
    }

    override fun setTitle(title: String) {
        actionBar!!.title = title
    }

}