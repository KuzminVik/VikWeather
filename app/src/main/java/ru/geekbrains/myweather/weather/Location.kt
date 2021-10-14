package ru.geekbrains.myweather.weather

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
)