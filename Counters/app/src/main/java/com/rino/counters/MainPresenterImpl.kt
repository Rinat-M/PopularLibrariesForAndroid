package com.rino.counters

import moxy.MvpPresenter

class MainPresenterImpl(
    private val model: CountersModel = CountersModel()
) : MainPresenter, MvpPresenter<MainView>() {

    override fun onCounter1Clicked() = viewState.setCounter1(model.next(0))

    override fun onCounter2Clicked() = viewState.setCounter2(model.next(1))

    override fun onCounter3Clicked() = viewState.setCounter3(model.next(2))

}

