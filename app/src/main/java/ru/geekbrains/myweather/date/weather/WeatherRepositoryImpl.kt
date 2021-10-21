package ru.geekbrains.myweather.date.weather

import android.annotation.SuppressLint
import io.reactivex.Observable
import ru.geekbrains.myweather.date.weather.datasource.CacheDataSource
import ru.geekbrains.myweather.date.weather.datasource.NetworkDataSource
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity
import ru.geekbrains.myweather.date.weather.models.WeatherModel
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val network: NetworkDataSource,
    private val cache: CacheDataSource
) : WeatherRepository {

    override fun getWeatherModelFromCacheByName(name: String): Observable<WeatherModel> {
        return Observable.zip(cache.getWeatherByName(name).toObservable(),
            cache.getDailyByName(name).toObservable(),
            { first, second ->
                WeatherModel(first, second)
            })
    }


    @SuppressLint("CheckResult")
    override fun getWeatherModelFromNetworkByName(name: String): Observable<WeatherModel> {
        return network.getLocationByCity(name)
            .flatMap { location ->
                network.getAllWeather(location.coord.lat, location.coord.lon).map { allWeather ->
                    mapLocationAndAllWeather(location, allWeather)
                }.map {
                    cache.insertDaily(it.daily).blockingGet()
                    cache.insertWeather(it.weatherEntity).blockingGet()
                    return@map it
                }
            }.toObservable()
    }

    // Пока не используется, будет нужна при определении координат телефона
    @SuppressLint("CheckResult")
    override fun getWeatherModelFromNetworkByCoord(
        lat: Double,
        lon: Double
    ): Observable<WeatherModel> =
        network.getLocationByCoord(lat, lon)
            .flatMap { location ->
                network.getAllWeather(location.coord.lat, location.coord.lon).map { allWeather ->
                    mapLocationAndAllWeather(location, allWeather)
                }.map {
                    cache.insertDaily(it.daily).blockingGet()
                    cache.insertWeather(it.weatherEntity).blockingGet()
                    return@map it
                }
            }.toObservable()

    private fun mapLocationAndAllWeather(l: Location, w: AllWeather): WeatherModel {
        val weather = WeatherEntity(
            id = l.id,
            name = l.name,
            lat = l.coord.lat,
            lon = l.coord.lon,
            dt = w.current.dt,
            sunrise = w.current.sunrise,
            sunset = w.current.sunset,
            temp = w.current.temp,
            feelsLike = w.current.feelsLike,
            pressure = w.current.pressure,
            humidity = w.current.humidity,
            dewPoint = w.current.dewPoint,
            uvi = w.current.uvi,
            clouds = w.current.clouds,
            visibility = w.current.visibility,
            windSpeed = w.current.windSpeed,
            windDeg = w.current.windDeg,
            description = w.current.weather[0].description,
            icon = w.current.weather[0].icon
        )
        val list = mutableListOf<DayEntity>()
        for (el in w.daily) {
            list.add(
                DayEntity(
                    parentName = l.name,
                    dt = el.dt,
                    tempDay = el.temp.day,
                    tempNight = el.temp.night,
                    pressure = el.pressure,
                    humidity = el.humidity,
                    description = el.weather[0].description,
                    icon = el.weather[0].icon
                )
            )
        }
        return WeatherModel(weather, list)
    }

}