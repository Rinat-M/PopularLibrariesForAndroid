package com.rino.githubusers.core.repository

import com.rino.githubusers.core.model.GithubRepos
import io.reactivex.rxjava3.core.Single

interface GithubReposRepository {
    fun getUserReposByLogin(login: String): Single<List<GithubRepos>>
    fun getUserRepoByUrl(url: String): Single<GithubRepos>
}