package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.geekbrains.myweather.date.weather.WeatherEntity

interface CacheDataSource {

    fun getWeatherByName(name: String): Maybe<WeatherEntity>

    fun retainWeather(weatherEntity: WeatherEntity): Maybe<WeatherEntity>

    fun deleteWeatherByName(weatherEntity: WeatherEntity): Completable

}