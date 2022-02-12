package com.rino.githubusers.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.core.repository.GithubUsersRepositoryImpl
import com.rino.githubusers.screens.IScreens
import com.rino.githubusers.core.model.GithubUser
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepositoryImpl: GithubUsersRepositoryImpl,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    companion object {
        private const val TAG = "UsersPresenter"
    }


    private lateinit var usersDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        usersDisposable = usersRepositoryImpl.getUsers()
            .subscribe(
                { users ->
                    viewState.updateList(users)
                },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )
    }

    fun onUserClicked(githubUser: GithubUser) {
        router.navigateTo(screens.user(githubUser.login))
    }

    fun backPressed(): Boolean {
        usersDisposable.dispose()
        router.exit()
        return true
    }
}
