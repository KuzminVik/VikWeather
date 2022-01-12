package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.HourEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity

interface CacheDataSource {

    fun getWeatherByName(name: String): Maybe<WeatherEntity>

    fun insertWeather(weatherEntity: WeatherEntity): Completable

    fun deleteWeather(weatherEntity: WeatherEntity): Completable

    fun deleteWeatherByName(name: String): Completable

    // Daily ========================================================

    fun getDailyByName(name: String): Maybe<List<DayEntity>>

    fun insertDaily(daily: List<DayEntity>): Completable

    fun deleteDaily(daily: List<DayEntity>): Completable

    fun deleteDailyByName(name: String): Completable

    // Hourly =======================================================

    fun getHourlyByName(name: String): Maybe<List<HourEntity>>

    fun insertHourly(hourly: List<HourEntity>): Completable

    fun deleteHourly(hourly: List<HourEntity>): Completable

    fun deleteHourlyByName(name: String): Completable

    // City =====================================================

    fun addCity(city: City): Completable

    fun getListCity(): Maybe<List<City>>

    fun deleteCity(city: City): Completable

}