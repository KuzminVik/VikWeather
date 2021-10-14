package ru.geekbrains.myweather.date.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ru.geekbrains.myweather.BuildConfig
import ru.geekbrains.myweather.weather.AllWeather

interface WeatherApi {

    @GET(value = "/data/2.5/onecall?lat={lat}&lon={lon}&exclude=daily,minutely,hourly,alerts&appid=${BuildConfig.APIKEY}&lang=ru&units=metric")
fun loadAllWeather(lat: Double, lon: Double): Single<AllWeather>

}