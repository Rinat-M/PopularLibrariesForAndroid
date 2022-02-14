package com.rino.githubusers.ui.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.model.GithubRepos
import com.rino.githubusers.repository.GithubUsersRepository
import com.rino.githubusers.screens.IScreens
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    private val login: String,
    private val usersRepository: GithubUsersRepository,
    private val router: Router,
    private val screen: IScreens
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
        userDisposable = usersRepository.getUserByLogin(login)
            .subscribe(
                { user -> viewState.updateUserInfo(user) },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )

    }

    private fun loadUserRepos() {
        reposDisposable = usersRepository.getUserReposByLogin(login)
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
        router.navigateTo(screen.userRepository(githubRepos.url))
    }

}
