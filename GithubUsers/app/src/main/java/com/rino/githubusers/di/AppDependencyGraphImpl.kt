package com.rino.githubusers.di

import android.content.Context
import android.util.Log
import com.rino.githubusers.App
import com.rino.githubusers.di.components.AppComponent
import com.rino.githubusers.di.components.DaggerAppComponent
import com.rino.githubusers.di.components.ReposSubcomponent
import com.rino.githubusers.di.components.UsersSubcomponent
import com.rino.githubusers.di.modules.ContextModule

class AppDependencyGraphImpl(context: Context) : AppDependencyGraph {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(context))
            .build()
    }

    private var _usersSubcomponent: UsersSubcomponent? = null
    private var _reposSubcomponent: ReposSubcomponent? = null

    override val usersSubcomponent: UsersSubcomponent
        get() {
            if (_usersSubcomponent == null) {
                _usersSubcomponent = appComponent.usersSubcomponent()
                Log.d(App.TAG, "initUsersSubcomponent done")
            }

            return _usersSubcomponent!!
        }

    override val reposSubcomponent: ReposSubcomponent
        get() {
            if (_reposSubcomponent == null) {
                _reposSubcomponent = appComponent.usersSubcomponent().reposSubcomponent()
                Log.d(App.TAG, "initReposSubcomponent done")
            }

            return _reposSubcomponent!!
        }

    override fun destroyReposScope() {
        _reposSubcomponent = null
        Log.d(App.TAG, "destroyReposScope completed")
    }

    override fun destroyUsersScope() {
        _usersSubcomponent = null
        Log.d(App.TAG, "destroyUsersScope completed")
    }
}