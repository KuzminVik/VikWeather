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
import ru.geekbrains.myweather.util.CITY_NAME
import ru.geekbrains.myweather.util.KEY_DAILY
import ru.geekbrains.myweather.util.KEY_HOURLY
import ru.geekbrains.myweather.util.TITLE_MY_WEATHER
import java.time.Instant

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
                val unixNow = Instant.now().toEpochMilli()/1000
                val unixSave = it.weatherEntity.dt
                if(unixNow - 1800 > unixSave){
                    repo.clearDataByName(name)
                    getDataFromNetwork(name)
                }
                else{
                    setData(it)
                }
            },{
                getDataFromNetwork(name)
            })
    }

    private fun setData(weatherModel: WeatherModel){
        viewState.showCurrentWeather(weatherModel.weatherEntity)
        saveNameCity(weatherModel.weatherEntity.name)
        viewState.setTitle(TITLE_MY_WEATHER)
        if(isEnabledDailyForecast()) viewState.showDaily(weatherModel.daily)
        if(isEnabledHourlyForecast()) viewState.showHourly(weatherModel.hourly)
    }

    @SuppressLint("CheckResult")
    private fun getDataFromNetwork(name: String){
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
    }

    /**
     * Сохраняет название последнего просмотренного города
     */
    private fun saveNameCity(name: String) =
        sharedPreferences.edit().putString(CITY_NAME, name).apply()

    /**
     * Данные из настроек приложения: показывать ли ежедневный прогноз на ближайшую неделю
     */
    private fun isEnabledDailyForecast() = sharedPreferences.getBoolean(KEY_DAILY, true)

    /**
     * Данные из настроек приложения: показывать ли почасовой прогноз
     */
    private fun isEnabledHourlyForecast() = sharedPreferences.getBoolean(KEY_HOURLY, false)

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}