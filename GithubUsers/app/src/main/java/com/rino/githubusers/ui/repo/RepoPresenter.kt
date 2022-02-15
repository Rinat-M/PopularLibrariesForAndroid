package com.rino.githubusers.ui.repo

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.core.repository.GithubReposRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class RepoPresenter @AssistedInject constructor(
    @Assisted private val repoUrl: String,
    private val router: Router,
    private val reposRepository: GithubReposRepository
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
        repoDisposable = reposRepository
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