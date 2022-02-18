package com.rino.githubusers

import android.app.Application
import com.rino.githubusers.di.AppDependencyGraph
import com.rino.githubusers.di.AppDependencyGraphImpl

class App : Application() {

    companion object {
        const val TAG = "App"
        lateinit var appDependencyGraph: AppDependencyGraph
    }

    override fun onCreate() {
        super.onCreate()
        appDependencyGraph = AppDependencyGraphImpl(this)
    }

}