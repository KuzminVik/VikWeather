package ru.geekbrains.myweather.presentation.currentweather

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.date.weather.models.WeatherModel
import ru.geekbrains.myweather.presentation.AndroidScreens
import ru.geekbrains.myweather.scheduler.ISchedulers

class CurrentWeatherPresenter(
    private val nameCity: String,
    private val repo: WeatherRepository,
    private val router: Router,
    private val schedulers: ISchedulers,
    private val sharedPreferences: SharedPreferences
): MvpPresenter<CurrentWeatherView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        loadWeatherByName(nameCity)
    }

    @SuppressLint("CheckResult")
    private fun loadWeatherByName(name: String){
        disposable +=
        repo
            .getWeatherModelFromCacheByName(name)
            .observeOn(schedulers.mainThread())
            .subscribeOn(schedulers.ioThread())
            .subscribe({
                setData(it)
            },{
                repo
                    .getWeatherModelFromNetworkByName(name)
                    .observeOn(schedulers.mainThread())
                    .subscribeOn(schedulers.ioThread())
                    .subscribe({
                        setData(it)
                    },{
                        viewState.showErrorCurrentWeather(it)
                        router.navigateTo(AndroidScreens().search())
                    })
            })
    }

    private fun setData(weatherModel: WeatherModel){
        viewState.showCurrentWeather(weatherModel.weatherEntity)
        saveNameCity(weatherModel.weatherEntity.name)
        viewState.setTitle("Моя погода")
        if(isEnabledDailyForecast()) viewState.showDaily(weatherModel.daily)
    }

    /**
     * Сохраняет название последнего просмотренного города
     */
    private fun saveNameCity(name: String) =
        sharedPreferences.edit().putString("cityName", name).apply()

    /**
     * Данные из настроек приложения: показывать ли ежедневный прогноз на ближайшую неделю
     */
    private fun isEnabledDailyForecast() = sharedPreferences.getBoolean("KEY_DAILY", true)

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}