package ru.geekbrains.myweather.date.weather

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ru.geekbrains.myweather.date.weather.datasource.CacheDataSource
import ru.geekbrains.myweather.date.weather.datasource.NetworkDataSource
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val network: NetworkDataSource,
    private val cache: CacheDataSource
): WeatherRepository {

    override fun getWeatherByName(name: String): Maybe<WeatherEntity> =
        cache.getWeatherByName(name)
            .onErrorResumeNext(
                network.getLocationByCity(name)
                    .flatMap { location ->
                        network.getAllWeather(location.coord.lat, location.coord.lon).map { allWeather ->
                            mapLocationAndAllWeather(location, allWeather)
                        }
                    }
            ).flatMap {
                cache.retainWeather(it)
            }

    override fun getWeatherByCoord(lat: Double, lon: Double): Maybe<WeatherEntity> =
        network.getLocationByCoord(lat, lon)
            .flatMap {  location ->
                network.getAllWeather(location.coord.lat, location.coord.lon).map { allWeather ->
                    mapLocationAndAllWeather(location, allWeather)
                }
            }

    private fun mapLocationAndAllWeather(l: Location, w: AllWeather): WeatherEntity{
        return WeatherEntity(
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
            icon = w.current.weather[0].icon)
    }
}