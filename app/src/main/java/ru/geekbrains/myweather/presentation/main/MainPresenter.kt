package ru.geekbrains.myweather.presentation.main

import moxy.MvpPresenter

class MainPresenter: MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        // сохранить координаты в базе данных
        val location = viewState.getLocation()
//        router.replaceScreen(AndroidScreens().users())
    }

    fun clickHome(){
//        router.navigateTo(AndroidScreens().users())
    }

    fun clickSearch() {
//        router.navigateTo(AndroidScreens().search())
    }

    fun clickSettings() {
//        router.navigateTo(AndroidScreens().convert())
    }

}