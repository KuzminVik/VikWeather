package ru.geekbrains.myweather.presentation.main

import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import io.reactivex.Single
import moxy.MvpPresenter
import ru.geekbrains.myweather.presentation.AndroidScreens
import ru.geekbrains.myweather.scheduler.ISchedulers

class MainPresenter(
    private val router: Router,
    private val schedulers: ISchedulers
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.getNameCity()
    }

    @SuppressLint("CheckResult")
    fun init(name: String){
        Single.just(name)
            .observeOn(schedulers.mainThread())
            .subscribeOn(schedulers.backgroundThread())
            .subscribe { it ->
                if (it.equals(" ") || it.equals("")){
                    router.navigateTo(AndroidScreens().search())
                }else{
                    router.replaceScreen(AndroidScreens().current(it))
                }
        }
    }

    fun clickHome(){
        viewState.getNameCity()
    }

    fun clickSearch() {
        router.navigateTo(AndroidScreens().search())
    }

    fun clickSettings() {
        router.navigateTo(AndroidScreens().setting())
    }
}