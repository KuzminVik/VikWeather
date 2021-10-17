package ru.geekbrains.myweather.date.weather

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherRepository {

    fun getWeatherByName(name: String): Maybe<WeatherEntity>

    fun getWeatherByCoord(lat: Double, lon: Double): Maybe<WeatherEntity>

}