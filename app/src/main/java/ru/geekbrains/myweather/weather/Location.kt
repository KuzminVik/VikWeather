package ru.geekbrains.myweather.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val lat: Double,
    val lon: Double
)
