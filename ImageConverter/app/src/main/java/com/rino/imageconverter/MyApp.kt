package com.rino.imageconverter

import android.app.Application
import android.util.Log
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class MyApp : Application() {

    companion object {
        private const val TAG = "MyApp"
    }

    override fun onCreate() {
        super.onCreate()
        setRxJavaErrorHandler()
    }

    private fun setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler { ex ->
            if (ex is UndeliverableException) {
                Log.d(TAG, "RxJavaErrorHandler", ex)
            } else {
                throw ex
            }
        }

    }
}