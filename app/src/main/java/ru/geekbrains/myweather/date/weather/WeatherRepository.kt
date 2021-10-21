package ru.geekbrains.myweather.date.weather

import io.reactivex.Observable
import ru.geekbrains.myweather.date.weather.models.WeatherModel

interface WeatherRepository {

    fun getWeatherModelFromCacheByName(name: String): Observable<WeatherModel>

    fun getWeatherModelFromNetworkByName(name: String): Observable<WeatherModel>

//    fun getWeatherByName(name: String): Maybe<WeatherEntity>

    fun getWeatherModelFromNetworkByCoord(lat: Double, lon: Double): Observable<WeatherModel>

}