package com.rino.githubusers

import android.app.Application
import com.rino.githubusers.database.GithubDatabase
import com.rino.githubusers.di.component.AppComponent
import com.rino.githubusers.di.component.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }

    val database by lazy {
        GithubDatabase.getInstance(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}