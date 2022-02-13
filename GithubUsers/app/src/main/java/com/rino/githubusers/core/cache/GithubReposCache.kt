package com.rino.githubusers.core.cache

import com.rino.githubusers.core.model.GithubRepos
import io.reactivex.rxjava3.core.Single

interface GithubReposCache {
    fun getUserReposByLogin(login: String): Single<List<GithubRepos>>
    fun getUserRepoByUrl(url: String): Single<GithubRepos>
}