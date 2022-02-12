package com.rino.githubusers.ui.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.model.GithubRepos
import com.rino.githubusers.repository.GithubUsersRepositoryImpl
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    private val login: String,
    private val usersRepositoryImpl: GithubUsersRepositoryImpl,
    private val router: Router
) : MvpPresenter<UserView>() {

    companion object {
        private const val TAG = "UserPresenter"
    }

    private lateinit var userDisposable: Disposable
    private lateinit var reposDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserInfo()
        loadUserRepos()
    }

    private fun loadUserInfo() {
        userDisposable = usersRepositoryImpl.getUserByLogin(login)
            .subscribe(
                { user -> viewState.updateUserInfo(user) },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )

    }

    private fun loadUserRepos() {
        reposDisposable = usersRepositoryImpl.getUserReposByLogin(login)
            .subscribe(
                { repos -> viewState.updateUserRepos(repos) },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )
    }

    fun backPressed(): Boolean {
        userDisposable.dispose()
        reposDisposable.dispose()
        router.exit()
        return true
    }

    fun onRepositoryClicked(githubRepos: GithubRepos) {
        //TODO
    }

}
