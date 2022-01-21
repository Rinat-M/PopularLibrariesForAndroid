package com.rino.counters

class MainPresenterImpl(private val view: MainView) : MainPresenter {
    private val model = CountersModel()

    override fun onCounter1Clicked() {
        val nextValue = model.next(0)
        view.setButtonText(0, nextValue.toString())
    }

    override fun onCounter2Clicked() {
        val nextValue = model.next(1)
        view.setButtonText(1, nextValue.toString())
    }

    override fun onCounter3Clicked() {
        val nextValue = model.next(2)
        view.setButtonText(2, nextValue.toString())
    }
}

