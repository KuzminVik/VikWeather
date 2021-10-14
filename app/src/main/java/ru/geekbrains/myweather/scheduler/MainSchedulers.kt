package ru.geekbrains.myweather.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class MainSchedulers: SchedulersThreads {

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun backgroundThread(): Scheduler = io.reactivex.schedulers.Schedulers.newThread()

    override fun ioThread(): Scheduler = io.reactivex.schedulers.Schedulers.io()

}