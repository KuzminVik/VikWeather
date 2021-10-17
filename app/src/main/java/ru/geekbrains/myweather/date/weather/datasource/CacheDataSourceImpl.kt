package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.geekbrains.myweather.date.storage.WeatherStorage
import ru.geekbrains.myweather.date.weather.WeatherEntity
import ru.geekbrains.myweather.di.InMemory
import javax.inject.Inject

class CacheDataSourceImpl
@Inject constructor(
    @InMemory private val weatherStorage: WeatherStorage
): CacheDataSource {

    override fun getWeatherByName(name: String): Maybe<WeatherEntity> =
        weatherStorage.weatherDao()
            .getWeatherByName(name).toMaybe()

    override fun retainWeather(weatherEntity: WeatherEntity): Maybe<WeatherEntity> =
        weatherStorage.weatherDao()
            .retainWeather(weatherEntity)
            .andThen(getWeatherByName(weatherEntity.name))

    override fun deleteWeatherByName(weatherEntity: WeatherEntity): Completable =
        weatherStorage.weatherDao()
            .deleteWeatherByName(weatherEntity)
}