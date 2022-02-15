package com.rino.githubusers.di.component

import com.rino.githubusers.di.factory.RepoPresenterFactory
import com.rino.githubusers.di.factory.UserPresenterFactory
import com.rino.githubusers.di.modules.*
import com.rino.githubusers.ui.main.MainActivity
import com.rino.githubusers.ui.main.MainPresenter
import com.rino.githubusers.ui.repo.RepoFragment
import com.rino.githubusers.ui.user.UserFragment
import com.rino.githubusers.ui.users.UsersFragment
import com.rino.githubusers.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CacheModule::class,
        DatabaseModule::class,
        ContextModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(repoFragment: RepoFragment)
    fun inject(userFragment: UserFragment)
    fun inject(usersFragment: UsersFragment)

    fun providesMainPresenter(): MainPresenter
    fun providesUsersPresenter(): UsersPresenter

    fun providesUserPresenterFactory(): UserPresenterFactory
    fun providesRepoPresenterFactory(): RepoPresenterFactory

}