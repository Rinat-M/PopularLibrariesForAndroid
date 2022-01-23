package com.rino.counters

class MainPresenterImpl(
    private val mainView: MainView,
    private val model: CountersModel = CountersModel()
) : MainPresenter {

    override fun onCounter1Clicked() = mainView.setCounter1(model.next(0))

    override fun onCounter2Clicked() = mainView.setCounter2(model.next(1))

    override fun onCounter3Clicked() = mainView.setCounter3(model.next(2))

}

