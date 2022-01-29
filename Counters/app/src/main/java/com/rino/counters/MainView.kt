package com.rino.counters

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView : MvpView {
    fun setCounter1(value: Int)
    fun setCounter2(value: Int)
    fun setCounter3(value: Int)
}
