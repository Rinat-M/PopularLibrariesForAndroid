package com.rino.githubusers.di.components

import com.rino.githubusers.di.factories.RepoPresenterFactory
import com.rino.githubusers.di.factories.UserPresenterFactory
import com.rino.githubusers.di.modules.ReposModule
import com.rino.githubusers.di.scopes.ReposScope
import dagger.Subcomponent

@Subcomponent(modules = [ReposModule::class])
@ReposScope
interface ReposSubcomponent {

    fun repoPresenterFactory(): RepoPresenterFactory

    fun userPresenterFactory(): UserPresenterFactory

}