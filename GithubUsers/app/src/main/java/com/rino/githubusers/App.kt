package com.rino.githubusers

import android.app.Application
import android.util.Log
import com.rino.githubusers.di.components.AppComponent
import com.rino.githubusers.di.components.DaggerAppComponent
import com.rino.githubusers.di.components.ReposSubcomponent
import com.rino.githubusers.di.components.UsersSubcomponent
import com.rino.githubusers.di.modules.ContextModule

class App : Application() {
    companion object {
        const val TAG = "App"
        lateinit var instance: App
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    var usersSubcomponent: UsersSubcomponent? = null
        private set
    var reposSubcomponent: ReposSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun initUsersSubcomponent(): UsersSubcomponent {
        usersSubcomponent = appComponent.usersSubcomponent()
        Log.d(TAG, "initUsersSubcomponent done")
        return usersSubcomponent!!
    }

    fun initReposSubcomponent(): ReposSubcomponent {
        reposSubcomponent = appComponent.usersSubcomponent().reposSubcomponent()
        Log.d(TAG, "initReposSubcomponent done")
        return reposSubcomponent!!
    }

    fun destroyReposScope() {
        reposSubcomponent = null
        Log.d(TAG, "destroyReposScope completed")
    }

    fun destroyUsersScope() {
        usersSubcomponent = null
        Log.d(TAG, "destroyUsersScope completed")
    }

}