package com.rino.githubusers.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.repository.GithubUsersRepository
import com.rino.githubusers.screens.IScreens
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val router: Router,
    private val screens: IScreens,
    private val usersRepositoryImpl: GithubUsersRepository
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
                { throwable ->
                    Log.e(TAG, throwable.stackTraceToString())
                    viewState.showMessage(throwable.message ?: "Can't load data")
                }
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
