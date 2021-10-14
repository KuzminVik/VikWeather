package ru.geekbrains.myweather.scheduler

import io.reactivex.Scheduler

interface SchedulersThreads {

    fun mainThread(): Scheduler
    fun backgroundThread(): Scheduler
    fun ioThread(): Scheduler

}