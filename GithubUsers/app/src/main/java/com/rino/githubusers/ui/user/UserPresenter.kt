package com.rino.githubusers.ui.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.repository.GithubUsersRepositoryImpl
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    private val userId: Long,
    private val usersRepositoryImpl: GithubUsersRepositoryImpl,
    private val router: Router
) : MvpPresenter<UserView>() {

    companion object {
        private const val TAG = "UserPresenter"
    }

    private lateinit var userDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        userDisposable = usersRepositoryImpl.getUserById(userId)
            .subscribe(
                { user -> viewState.updateView(user) },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )

    }

    fun backPressed(): Boolean {
        userDisposable.dispose()
        router.exit()
        return true
    }

}
