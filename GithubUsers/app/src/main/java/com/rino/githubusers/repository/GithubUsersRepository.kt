package com.rino.githubusers.repository

import com.rino.githubusers.model.GithubRepos
import com.rino.githubusers.model.GithubUser
import com.rino.githubusers.model.GithubUserDetailed
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Single<GithubUserDetailed>
    fun getUserReposByLogin(login: String): Single<List<GithubRepos>>
    fun getUserRepoByUrl(url: String): Single<GithubRepos>
}