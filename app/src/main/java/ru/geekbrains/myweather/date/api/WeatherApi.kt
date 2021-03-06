package ru.geekbrains.myweather.date.api

import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.myweather.date.weather.models.AllWeather
import ru.geekbrains.myweather.date.weather.models.Location

interface WeatherApi {

    @GET(value = "/data/2.5/onecall?&exclude=minutely,alerts&lang=ru&units=metric")
    fun loadAllWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Maybe<AllWeather>

    @GET(value = "/data/2.5/weather?&lang=ru&units=metric")
    fun loadLocationByCity(
        @Query("q") city: String,
        @Query("appid") appid: String
    ): Maybe<Location>

    @GET(value = "/data/2.5/weather?&lang=ru&units=metric")
    fun loadLocationByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Maybe<Location>
}
