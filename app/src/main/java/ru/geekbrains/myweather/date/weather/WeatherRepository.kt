package ru.geekbrains.myweather.date.weather

import io.reactivex.Completable
import io.reactivex.Observable
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.date.weather.models.WeatherModel

interface WeatherRepository {

    fun getWeatherModelFromCacheByName(name: String): Observable<WeatherModel>

    fun getWeatherModelFromNetworkByName(name: String): Observable<WeatherModel>

    fun getWeatherModelFromNetworkByCoord(lat: Double, lon: Double): Observable<WeatherModel>

    fun getListCity(): Observable<List<City>>

    fun clearDataByName(name: String)

}