package com.rino.imageconverter

import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {

    fun selectImage() {
        viewState.selectImage()
    }
}