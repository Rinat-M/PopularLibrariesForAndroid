package com.rino.githubusers.repository

import com.rino.githubusers.model.GithubUser
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

    override fun getUserById(id: Long): Single<GithubUser> {
        return Single.create {
            //TODO
        }
    }
}

