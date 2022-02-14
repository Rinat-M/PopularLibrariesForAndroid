package com.rino.githubusers.core.cache

import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.model.GithubUserDetailed
import io.reactivex.rxjava3.core.Single

interface GithubUsersCache {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Single<GithubUserDetailed>
}