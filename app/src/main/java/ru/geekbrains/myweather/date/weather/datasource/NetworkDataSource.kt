package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Maybe
import ru.geekbrains.myweather.date.weather.models.AllWeather
import ru.geekbrains.myweather.date.weather.models.Location

interface NetworkDataSource {

    fun getAllWeather(lat: Double, lon: Double): Maybe<AllWeather>

    fun getLocationByCity(city: String): Maybe<Location>

    fun getLocationByCoord(lat: Double, lon: Double): Maybe<Location>

}