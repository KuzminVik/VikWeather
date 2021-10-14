package ru.geekbrains.myweather.date.api

import io.reactivex.Single
import retrofit2.http.GET
import ru.geekbrains.myweather.BuildConfig
import ru.geekbrains.myweather.weather.AllWeather
import ru.geekbrains.myweather.weather.Location

interface WeatherApi {

    @GET(value = "/data/2.5/onecall?lat={lat}&lon={lon}&exclude=daily,minutely,hourly,alerts&appid=${BuildConfig.APIKEY}&lang=ru&units=metric")
    fun loadAllWeather(lat: Double, lon: Double): Single<AllWeather>

    @GET(value = "/data/2.5/weather?q={city}&appid=${BuildConfig.APIKEY}&lang=ru&units=metric")
    fun loadLocationByCity(city: String): Single<Location>

    @GET(value = "/data/2.5/weather?lat={lat}&lon={lon}&appid=${BuildConfig.APIKEY}&lang=ru&units=metric")
    fun loadLocationByCoord(lat: Double, lon: Double): Single<Location>
}
