package com.rino.githubusers.ui.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.App
import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.core.repository.GithubReposRepository
import com.rino.githubusers.core.repository.GithubUsersRepository
import com.rino.githubusers.screens.IScreens
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter @AssistedInject constructor(
    @Assisted private val login: String,
    private val router: Router,
    private val screen: IScreens,
    private val usersRepository: GithubUsersRepository,
    private val reposRepository: GithubReposRepository
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

    override fun onDestroy() {
        super.onDestroy()
        App.appDependencyGraph.destroyReposScope()
    }

    private fun loadUserInfo() {
        userDisposable = usersRepository.getUserByLogin(login)
            .subscribe(
                { user -> viewState.updateUserInfo(user) },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )

    }

    private fun loadUserRepos() {
        reposDisposable = reposRepository.getUserReposByLogin(login)
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
