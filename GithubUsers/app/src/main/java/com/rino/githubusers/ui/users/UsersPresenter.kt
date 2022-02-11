package com.rino.githubusers.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.repository.GithubUsersRepository
import com.rino.githubusers.screens.IScreens
import com.rino.githubusers.model.GithubUser
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepository: GithubUsersRepository,
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
        usersDisposable = usersRepository.getUsers()
            .subscribe(
                { users ->
                    viewState.updateList(users)
                },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )
    }

    fun onUserClicked(githubUser: GithubUser) {
        router.navigateTo(screens.user(githubUser.id))
    }

    fun backPressed(): Boolean {
        usersDisposable.dispose()
        router.exit()
        return true
    }
}
