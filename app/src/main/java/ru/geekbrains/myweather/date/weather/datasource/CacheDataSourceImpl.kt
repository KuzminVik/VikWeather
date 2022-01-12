package ru.geekbrains.myweather.date.weather.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.geekbrains.myweather.date.storage.WeatherStorage
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.date.weather.models.DayEntity
import ru.geekbrains.myweather.date.weather.models.HourEntity
import ru.geekbrains.myweather.date.weather.models.WeatherEntity
import ru.geekbrains.myweather.di.DbInMemory
import ru.geekbrains.myweather.di.DbPersisted
import javax.inject.Inject

class CacheDataSourceImpl
@Inject constructor(
    private val weatherStorage: WeatherStorage
): CacheDataSource {

    override fun getWeatherByName(name: String): Maybe<WeatherEntity> =
        weatherStorage.weatherDao()
            .getWeatherByName(name).toMaybe()

    override fun insertWeather(weatherEntity: WeatherEntity): Completable =
        weatherStorage.weatherDao()
            .insertWeather(weatherEntity)

    override fun deleteWeather(weatherEntity: WeatherEntity): Completable =
        weatherStorage.weatherDao()
            .deleteWeather(weatherEntity)

    override fun deleteWeatherByName(name: String): Completable =
        weatherStorage.weatherDao()
            .deleteWeatherByName(name)

    // Daily ========================================================

    override fun getDailyByName(name: String): Maybe<List<DayEntity>> =
        weatherStorage.weatherDao()
            .getDailyByName(name).toMaybe()

    override fun insertDaily(daily: List<DayEntity>): Completable =
        weatherStorage.weatherDao()
            .insertDaily(daily)

    override fun deleteDaily(daily: List<DayEntity>): Completable =
        weatherStorage.weatherDao()
            .deleteDaily(daily)

    override fun deleteDailyByName(name: String): Completable =
        weatherStorage.weatherDao()
            .deleteDailyByName(name)

    // Hourly ========================================================

    override fun getHourlyByName(name: String): Maybe<List<HourEntity>> =
        weatherStorage.weatherDao()
            .getHourlyByName(name).toMaybe()


    override fun insertHourly(hourly: List<HourEntity>): Completable =
        weatherStorage.weatherDao()
            .insertHourly(hourly)

    override fun deleteHourly(hourly: List<HourEntity>): Completable =
        weatherStorage.weatherDao()
            .deleteHourly(hourly)

    override fun deleteHourlyByName(name: String): Completable =
        weatherStorage.weatherDao()
            .deleteHourlyByName(name)

    // City =======================================================

    override fun addCity(city: City): Completable =
        weatherStorage.weatherDao()
            .insertCity(city)

    override fun getListCity(): Maybe<List<City>> =
        weatherStorage.weatherDao()
            .getListCity().toMaybe()

    override fun deleteCity(city: City): Completable =
        weatherStorage.weatherDao()
            .deleteCity(city)

}