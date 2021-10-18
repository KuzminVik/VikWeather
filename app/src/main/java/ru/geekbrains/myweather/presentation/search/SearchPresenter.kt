package ru.geekbrains.myweather.presentation.search

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.myweather.date.weather.WeatherRepository
import ru.geekbrains.myweather.presentation.AndroidScreens
import ru.geekbrains.myweather.scheduler.ISchedulers

class SearchPresenter(
    private val repo: WeatherRepository,
    private val router: Router,
    private val schedulers: ISchedulers
): MvpPresenter<SearchView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        // Загрузить список ранее просмотренных городов
    }

    fun clickCity(city: String){
        if (validateNameCity(city)) {
            router.navigateTo(AndroidScreens().current(city))
        }else{
            viewState.showErrorCity("Ошибка в названии города")
        }
    }

    // Валидация на два пробела подряд
    private fun validateNameCity(s: String): Boolean {
        var count = 0
        for (i in s.indices) {
            if (s[i] == ' ' && count == 0) {
                count++
            } else if (s[i] != ' ' && count == 1) {
                count = 0
            } else if (s[i] == ' ' && count != 0) {
                return false
            }
        }
        return true
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}
