package com.rino.githubusers.di

import com.rino.githubusers.di.components.AppComponent
import com.rino.githubusers.di.components.ReposSubcomponent
import com.rino.githubusers.di.components.UsersSubcomponent

interface AppDependencyGraph {
    val appComponent: AppComponent
    val usersSubcomponent: UsersSubcomponent
    val reposSubcomponent: ReposSubcomponent

    fun destroyReposScope()
    fun destroyUsersScope()
}