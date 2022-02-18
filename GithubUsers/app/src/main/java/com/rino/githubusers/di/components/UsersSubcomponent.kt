package com.rino.githubusers.di.components

import com.rino.githubusers.di.modules.UsersModule
import com.rino.githubusers.di.scopes.UsersScope
import com.rino.githubusers.ui.users.UsersPresenter
import dagger.Subcomponent

@Subcomponent(modules = [UsersModule::class])
@UsersScope
interface UsersSubcomponent {

    fun reposSubcomponent(): ReposSubcomponent

    fun usersPresenter(): UsersPresenter

}