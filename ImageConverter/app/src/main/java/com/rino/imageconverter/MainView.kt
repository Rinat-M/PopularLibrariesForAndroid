package com.rino.imageconverter

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@OneExecution
interface MainView : MvpView {
    @AddToEndSingle
    fun showImage()

    fun selectImage()
    fun convertImageToPng()
    fun convertImageToPngAfterCheckingPermission()
}