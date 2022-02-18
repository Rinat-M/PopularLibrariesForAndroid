package com.rino.githubusers.di.components

import com.rino.githubusers.di.factories.RepoPresenterFactory
import com.rino.githubusers.di.factories.UserPresenterFactory
import com.rino.githubusers.di.modules.*
import com.rino.githubusers.ui.main.MainActivity
import com.rino.githubusers.ui.main.MainPresenter
import com.rino.githubusers.ui.user.UserFragment
import com.rino.githubusers.ui.users.UsersFragment
import com.rino.githubusers.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class,
        ImageModule::class,
        DatabaseModule::class,
        ContextModule::class
    ]
)
@Singleton
interface AppComponent {

    fun usersSubcomponent(): UsersSubcomponent

    fun inject(mainActivity: MainActivity)

    fun inject(userFragment: UserFragment)
    fun inject(usersFragment: UsersFragment)

    fun providesMainPresenter(): MainPresenter

}