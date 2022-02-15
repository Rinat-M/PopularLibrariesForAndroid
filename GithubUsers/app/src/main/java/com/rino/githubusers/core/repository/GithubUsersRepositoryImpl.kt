package com.rino.githubusers.core.repository

import com.rino.githubusers.core.cache.GithubUsersCache
import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.model.GithubUserDetailed
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GithubUsersRepositoryImpl @Inject constructor(
    private val githubUsersCache: GithubUsersCache
) : GithubUsersRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return githubUsersCache.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserByLogin(login: String): Single<GithubUserDetailed> {
        return githubUsersCache.getUserByLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}

