package com.rino.githubusers.repository

import com.rino.githubusers.model.GithubRepos
import com.rino.githubusers.model.GithubUser
import com.rino.githubusers.model.GithubUserDetailed
import com.rino.githubusers.network.GithubApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepositoryImpl(
    private val githubApiService: GithubApiService
) : GithubUsersRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return githubApiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserByLogin(login: String): Single<GithubUserDetailed> {
        return githubApiService.getUserByLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

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

