package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity

interface CacheDataSource {

    fun getWeatherByName(name: String): Maybe<WeatherEntity>

    fun insertWeather(weatherEntity: WeatherEntity): Maybe<WeatherEntity>

    fun deleteWeather(weatherEntity: WeatherEntity): Completable

    fun deleteWeatherByName(name: String): Completable

    // ==========================================================

    fun getDailyByName(name: String): Maybe<List<DayEntity>>

    fun insertDaily(daily: List<DayEntity>): Maybe<List<DayEntity>>

    fun deleteDaily(daily: List<DayEntity>): Completable

    fun deleteDailyByName(name: String): Completable



}