package ru.geekbrains.myweather.date.weather

import android.annotation.SuppressLint
import io.reactivex.Observable
import ru.geekbrains.myweather.date.weather.datasource.CacheDataSource
import ru.geekbrains.myweather.date.weather.datasource.NetworkDataSource
import ru.geekbrains.myweather.date.weather.models.*
import ru.geekbrains.myweather.util.roundingDouble
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val network: NetworkDataSource,
    private val cache: CacheDataSource
) : WeatherRepository {

    override fun getWeatherModelFromCacheByName(name: String): Observable<WeatherModel> {
        return Observable.zip(
            cache.getWeatherByName(name).toObservable(),
            cache.getDailyByName(name).toObservable(),
            cache.getHourlyByName(name).toObservable(),
            { first, second, third ->
                insertCity(first.name)
                WeatherModel(first, second, mapHourEntityToPair(third))
            })
    }

    override fun getWeatherModelFromNetworkByName(name: String): Observable<WeatherModel> {
        return network.getLocationByCity(name)
            .flatMap { location ->
                network.getAllWeather(location.coord.lat, location.coord.lon).map { allWeather ->
                    mapLocationAndAllWeather(location, allWeather)
                }.map {
                    insertDataToDb(it)
                    insertCity(it.weatherEntity.name)
                    return@map it
                }
            }.toObservable()
    }

    // Пока не используется, будет нужна при определении координат телефона
    override fun getWeatherModelFromNetworkByCoord(
        lat: Double,
        lon: Double
    ): Observable<WeatherModel> =
        network.getLocationByCoord(lat, lon)
            .flatMap { location ->
                network.getAllWeather(location.coord.lat, location.coord.lon).map { allWeather ->
                    mapLocationAndAllWeather(location, allWeather)
                }.map {
                    insertDataToDb(it)
                    insertCity(it.weatherEntity.name)
                    return@map it
                }
            }.toObservable()

    @SuppressLint("CheckResult")
    override fun getListCity(): Observable<List<City>> =
        cache.getListCity().map {
            if (it.size == 11){
                cache.deleteCity(it[0]).blockingAwait()
            }
            return@map it
        }.toObservable()

    override fun clearDataByName(name: String) {
        cache.deleteWeatherByName(name).blockingAwait()
        cache.deleteDailyByName(name).blockingAwait()
        cache.deleteHourlyByName(name).blockingAwait()
    }

    private fun mapLocationAndAllWeather(l: Location, w: AllWeather): WeatherModel {
        val weather = WeatherEntity(
            id = l.id, name = l.name, lat = l.coord.lat, lon = l.coord.lon,
            dt = w.current.dt, sunrise = w.current.sunrise, sunset = w.current.sunset,
            temp = roundingDouble(w.current.temp).toString(),
            tempDay = roundingDouble(w.daily[0].temp.day).toString(),
            tempNight = roundingDouble(w.daily[0].temp.night).toString(),
            tempEve = roundingDouble(w.daily[0].temp.eve).toString(),
            tempMorn = roundingDouble(w.daily[0].temp.morn).toString(),
            feelsLike = roundingDouble(w.current.feelsLike).toString(),
            pressure = (w.current.pressure*0.75).toString(),
            humidity = w.current.humidity.toString(),
            dewPoint = roundingDouble(w.current.dewPoint).toString(),
            uvi = w.current.uvi.toString(),
            clouds = w.current.clouds.toString(),
            visibility = w.current.visibility.toString(),
            windSpeed = w.current.windSpeed.toString(),
            windDeg = w.current.windDeg.toString(),
            description = w.current.weather[0].description,
            icon = w.current.weather[0].icon
        )
        val daily = mutableListOf<DayEntity>()
        for (el in w.daily) {
            daily.add(
                DayEntity(
                    parentName = l.name,
                    dt = el.dt,
                    tempDay = roundingDouble(el.temp.day).toString(),
                    tempNight = roundingDouble(el.temp.night).toString(),
                    tempEve = roundingDouble(el.temp.eve).toString(),
                    tempMorn = roundingDouble(el.temp.morn).toString(),
                    pressure = (el.pressure*0.75).toString(),
                    humidity = el.humidity.toString(),
                    description = el.weather[0].description,
                    icon = el.weather[0].icon
                )
            )
        }
        daily.removeFirst()
        val hourly = mutableListOf<Pair<HourEntity, Boolean>>()
        for (el in w.hourly){
            hourly.add(
                Pair(
                    HourEntity(
                        parentName = l.name,
                        dt = el.dt,
                        temp = roundingDouble(el.temp).toString(),
                        pressure = (el.pressure*0.75).toString(),
                        humidity = el.humidity.toString(),
                        clouds = el.clouds.toString(),
                        visibility = el.visibility.toString(),
                        windSpeed = el.windSpeed.toString(),
                        description = el.weather[0].description,
                        icon = el.weather[0].icon
                    ),
                    false
                )
            )
        }
        return WeatherModel(weather, daily, hourly)
    }

    @SuppressLint("CheckResult")
    private fun insertDataToDb(it: WeatherModel){
        cache.insertDaily(it.daily).blockingAwait()
        cache.insertWeather(it.weatherEntity).blockingAwait()
        cache.insertHourly(mapPairToHourEntity(it.hourly)).blockingAwait()
    }

    private fun mapHourEntityToPair(listEntity: List<HourEntity>): List<Pair<HourEntity, Boolean>>{
        val hourly = mutableListOf<Pair<HourEntity, Boolean>>()
        for (el in listEntity){ hourly.add(Pair(el, false)) }
        return hourly
    }

    private fun mapPairToHourEntity(listPair: List<Pair<HourEntity, Boolean>>): List<HourEntity>{
        val list = mutableListOf<HourEntity>()
        for (el in listPair){ list.add(el.first) }
        return list
    }

    private fun insertCity(name: String){
        cache.addCity(City(name = name)).blockingAwait()
    }

}