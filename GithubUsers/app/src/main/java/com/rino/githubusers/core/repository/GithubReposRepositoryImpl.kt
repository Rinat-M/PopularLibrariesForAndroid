package com.rino.githubusers.core.repository

import com.rino.githubusers.core.cache.GithubReposCache
import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.network.GithubApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubReposRepositoryImpl(
    private val githubReposCache: GithubReposCache
) : GithubReposRepository {

    override fun getUserReposByLogin(login: String): Single<List<GithubRepos>> {
        return githubReposCache.getUserReposByLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserRepoByUrl(url: String): Single<GithubRepos> {
        return githubReposCache.getUserRepoByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}