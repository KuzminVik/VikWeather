package ru.geekbrains.myweather.presentation.currentweather

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class CurrentWeatherPresenter: MvpPresenter<CurrentWeatherView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}