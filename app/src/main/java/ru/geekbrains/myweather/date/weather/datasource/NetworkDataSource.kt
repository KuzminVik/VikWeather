package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import ru.geekbrains.myweather.date.weather.AllWeather
import ru.geekbrains.myweather.date.weather.Location

interface NetworkDataSource {

    fun getAllWeather(lat: Double, lon: Double): Maybe<AllWeather>

    fun getLocationByCity(city: String): Maybe<Location>

    fun getLocationByCoord(lat: Double, lon: Double): Maybe<Location>

}