package com.rino.githubusers.ui.repo

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.repository.GithubUsersRepository
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class RepoPresenter(
    private val repoUrl: String,
    private val router: Router,
    private val usersRepository: GithubUsersRepository
) : MvpPresenter<RepoView>() {

    companion object {
        private const val TAG = "RepoPresenter"
    }

    private lateinit var repoDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        repoDisposable = usersRepository
            .getUserRepoByUrl(repoUrl)
            .subscribe(
                { githubRepos -> viewState.updateRepoInfo(githubRepos) },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )
    }

    fun backPressed(): Boolean {
        repoDisposable.dispose()
        router.exit()
        return true
    }
}