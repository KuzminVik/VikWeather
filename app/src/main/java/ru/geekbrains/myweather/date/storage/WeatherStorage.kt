package ru.geekbrains.myweather.date.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.myweather.date.weather.WeatherEntity

@Database(exportSchema = false, entities = [WeatherEntity::class], version = 1)
abstract class WeatherStorage: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}