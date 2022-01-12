package ru.geekbrains.myweather.util

fun roundingDouble(num: Double): Int{
    return if (num%1 >= 5) num.toInt()+1
    else num.toInt()
}

