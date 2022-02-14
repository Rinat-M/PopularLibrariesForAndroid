package com.rino.githubusers.core.repository

import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.model.GithubUserDetailed
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Single<GithubUserDetailed>
}