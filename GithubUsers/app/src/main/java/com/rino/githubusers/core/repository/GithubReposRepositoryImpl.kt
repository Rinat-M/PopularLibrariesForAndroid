package com.rino.githubusers.core.repository

import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.network.GithubApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubReposRepositoryImpl(
    private val githubApiService: GithubApiService
) : GithubReposRepository {

    override fun getUserReposByLogin(login: String): Single<List<GithubRepos>> {
        return githubApiService.getUserReposByLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserRepoByUrl(url: String): Single<GithubRepos> {
        return githubApiService.getRepoByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}