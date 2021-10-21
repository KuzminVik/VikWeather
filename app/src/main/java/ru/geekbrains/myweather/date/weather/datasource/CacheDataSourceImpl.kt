package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import ru.geekbrains.myweather.date.storage.WeatherStorage
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity
import ru.geekbrains.myweather.di.DbInMemory
import javax.inject.Inject

class CacheDataSourceImpl
@Inject constructor(
    @DbInMemory private val weatherStorage: WeatherStorage
): CacheDataSource {

    override fun getWeatherByName(name: String): Maybe<WeatherEntity> =
        weatherStorage.weatherDao()
            .getWeatherByName(name).toMaybe()

    override fun insertWeather(weatherEntity: WeatherEntity): Maybe<WeatherEntity> =
        weatherStorage.weatherDao()
            .insertWeather(weatherEntity).andThen(getWeatherByName(weatherEntity.name))

    override fun deleteWeather(weatherEntity: WeatherEntity): Completable =
        weatherStorage.weatherDao()
            .deleteWeather(weatherEntity)

    override fun deleteWeatherByName(name: String): Completable =
        weatherStorage.weatherDao()
            .deleteWeatherByName(name)

    // ==========================================================

    override fun getDailyByName(name: String): Maybe<List<DayEntity>> =
        weatherStorage.weatherDao()
            .getDailyByName(name).toMaybe()

    override fun insertDaily(daily: List<DayEntity>): Maybe<List<DayEntity>> =
        weatherStorage.weatherDao()
            .insertDaily(daily)
            .andThen(getDailyByName(daily[0].parentName))

    override fun deleteDaily(daily: List<DayEntity>): Completable =
        weatherStorage.weatherDao()
            .deleteDaily(daily)

    override fun deleteDailyByName(name: String): Completable =
        weatherStorage.weatherDao()
            .deleteDailyByName(name)
}