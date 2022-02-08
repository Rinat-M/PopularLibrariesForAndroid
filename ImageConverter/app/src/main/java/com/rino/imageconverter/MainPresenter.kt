package com.rino.imageconverter

import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {

    fun selectImage() = viewState.selectImage()

    fun showImage() = viewState.showImage()

    fun convertImageToPng() = viewState.convertImageToPng()

    fun convertImageToPngOnBackground() = viewState.convertImageToPngOnBackground()

    fun convertImageToPngAfterCheckingPermission() = viewState.convertImageToPngAfterCheckingPermission()

    fun showProgressDialog() = viewState.showProgressDialog()

    fun hideProgressDialog() = viewState.hideProgressDialog()

}