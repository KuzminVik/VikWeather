package ru.geekbrains.myweather.presentation.currentweather

import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.scheduler.ISchedulers
import ru.geekbrains.myweather.scheduler.MainSchedulers

class CurrentWeatherPresenter(
    private val nameCity: String,
    private val repo: WeatherRepository,
    private val router: Router,
    private val schedulers: ISchedulers
): MvpPresenter<CurrentWeatherView>() {

    private val disposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        loadCurrentWeatherByName(nameCity)
    }

    fun loadCurrentWeatherByName(name: String){
        disposable +=
        repo
            .getWeatherByName(name)
            .observeOn(schedulers.mainThread())
            .subscribeOn(schedulers.ioThread())
            .subscribe(
                viewState::showCurrentWeather,
                viewState::showErrorCurrentWeather
            )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}