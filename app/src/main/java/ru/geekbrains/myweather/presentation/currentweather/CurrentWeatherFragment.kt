package ru.geekbrains.myweather.presentation.currentweather

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import ru.geekbrains.myweather.databinding.FragmentCurrentWeatherBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweather.presentation.abs.AbsFragment
import ru.geekbrains.myweather.R.layout.fragment_current_weather
import ru.geekbrains.myweather.date.weather.WeatherEntity
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.presentation.arguments
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

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var repo: WeatherRepository

    @Suppress("unused")
    private val presenter: CurrentWeatherPresenter by moxyPresenter {
        CurrentWeatherPresenter(
            nameCity = nameCity,
            repo = repo,
            router = router,
            schedulers = schedulers
        )
    }

    @SuppressLint("SetTextI18n")
    override fun showCurrentWeather(weatherEntity: WeatherEntity) {
        viewBinding.tvData.text = convertUnixTime(weatherEntity.dt)
        viewBinding.tvCity.text = weatherEntity.name
        viewBinding.tvTemp.text = "${weatherEntity.temp} °C"
        viewBinding.tvTempFeel.text = "Ощущается как ${weatherEntity.feelsLike} °C"
        viewBinding.tvDesc.text = weatherEntity.description
        viewBinding.tvPressure.text = weatherEntity.pressure.toString()
        viewBinding.tvHumidity.text = weatherEntity.humidity.toString()
        viewBinding.tvDewPoint.text = weatherEntity.dewPoint.toString()
        viewBinding.tvCloud.text = weatherEntity.clouds.toString()
        viewBinding.tvVisibility.text = weatherEntity.visibility.toString()
        viewBinding.tvWind.text = weatherEntity.windSpeed.toString()
        val uri: Uri = Uri.parse("http://openweathermap.org/img/wn/${weatherEntity.icon}@2x.png")
        Glide.with(this)
            .load(uri)
            .into(viewBinding.ivIcon)
        hideProgressBar()
    }

    fun hideProgressBar(){
        viewBinding.constraintlayoutTop.visibility = View.VISIBLE
        viewBinding.constraintlayoutCenter.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.GONE
    }

    override fun showErrorCurrentWeather(e: Throwable?) {
        e?.message?.let {
            Log.d(TAG, it)
            Toast.makeText(requireContext(),"ERROR $TAG: $it", Toast.LENGTH_LONG).show()
        }
    }

    fun convertUnixTime(unixTime: Long): String{
        return java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(unixTime))
    }

    override fun saveNameCity(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("cityName", name)
        editor.apply()
    }

    override fun setTitle(title: String) {
        actionBar.title = title
    }

}