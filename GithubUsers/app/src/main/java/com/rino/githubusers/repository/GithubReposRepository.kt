package com.rino.githubusers.repository

import com.rino.githubusers.model.GithubRepos
import io.reactivex.rxjava3.core.Single

interface GithubReposRepository {
    fun getUserReposByLogin(login: String): Single<List<GithubRepos>>
    fun getUserRepoByUrl(url: String): Single<GithubRepos>
}