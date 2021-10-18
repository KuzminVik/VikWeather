package ru.geekbrains.myweather.presentation.currentweather

import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.presentation.AndroidScreens
import ru.geekbrains.myweather.scheduler.ISchedulers

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
            .subscribe ({ it ->
                viewState.showCurrentWeather(it)
                viewState.saveNameCity(it.name)
                viewState.setTitle("Моя погода")
            },{
                viewState.showErrorCurrentWeather(it)
                router.navigateTo(AndroidScreens().search())
        })
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}