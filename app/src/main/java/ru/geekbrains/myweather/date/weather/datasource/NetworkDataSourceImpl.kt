package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Maybe
import ru.geekbrains.myweather.BuildConfig
import ru.geekbrains.myweather.date.api.WeatherApi
import ru.geekbrains.myweather.date.weather.AllWeather
import ru.geekbrains.myweather.date.weather.Location
import javax.inject.Inject

class NetworkDataSourceImpl
@Inject constructor(
     private val weatherApi: WeatherApi
): NetworkDataSource {

    override fun getAllWeather(lat: Double, lon: Double): Maybe<AllWeather> =
        weatherApi.loadAllWeather(lat, lon, BuildConfig.APIKEY)

    override fun getLocationByCity(city: String): Maybe<Location> =
        weatherApi.loadLocationByCity(city, BuildConfig.APIKEY)

    override fun getLocationByCoord(lat: Double, lon: Double): Maybe<Location> =
        weatherApi.loadLocationByCoord(lat, lon, BuildConfig.APIKEY)
}