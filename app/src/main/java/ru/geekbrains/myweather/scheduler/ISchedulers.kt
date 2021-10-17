package ru.geekbrains.myweather.scheduler

import io.reactivex.Scheduler

interface ISchedulers {

    fun mainThread(): Scheduler
    fun backgroundThread(): Scheduler
    fun ioThread(): Scheduler

}