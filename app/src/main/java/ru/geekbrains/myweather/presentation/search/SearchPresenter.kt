package ru.geekbrains.myweather.presentation.search

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.date.weather.models.City
import ru.geekbrains.myweather.presentation.AndroidScreens
import ru.geekbrains.myweather.scheduler.ISchedulers
import ru.geekbrains.myweather.util.ERROR_NAME_CITY

class SearchPresenter(
    private val repo: WeatherRepository,
    private val router: Router,
    private val schedulers: ISchedulers
): MvpPresenter<SearchView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposable +=
            repo
                .getListCity()
                .observeOn(schedulers.mainThread())
                .subscribeOn(schedulers.ioThread())
                .subscribe({
                    viewState.showListCity(it)
                },{
                   viewState.hideListCity()
                })
    }

    fun clickCity(city: String){
        router.navigateTo(AndroidScreens().current(city))
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
