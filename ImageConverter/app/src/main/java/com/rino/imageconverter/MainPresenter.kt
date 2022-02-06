package com.rino.imageconverter

import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {

    fun selectImage() = viewState.selectImage()

    fun showImage() = viewState.showImage()

    fun convertImageToPng() = viewState.convertImageToPng()

    fun convertImageToPngAfterCheckingPermission() = viewState.convertImageToPngAfterCheckingPermission()

}