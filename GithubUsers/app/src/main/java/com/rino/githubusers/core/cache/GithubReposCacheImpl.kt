package com.rino.githubusers.core.cache

import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.database.dao.ReposDao
import com.rino.githubusers.network.GithubApiService
import com.rino.githubusers.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubReposCacheImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val githubApiService: GithubApiService,
    private val reposDao: ReposDao
) : GithubReposCache {

    override fun getUserReposByLogin(login: String): Single<List<GithubRepos>> {
        return networkStatus.isOnlineSingle
            .flatMap { isOnline ->
                if (isOnline) {
                    githubApiService.getUserReposByLogin(login)
                        .flatMap { repos ->
                            val reposDb = repos.map { it.dbModel }
                            reposDao.insertAll(reposDb)
                            Single.just(repos)
                        }
                } else {
                    reposDao.getUserReposByLogin(login)
                        .map { reposDb -> reposDb.map { it.coreModel } }
                }
            }
    }

    override fun getUserRepoByUrl(url: String): Single<GithubRepos> {
        return networkStatus.isOnlineSingle
            .flatMap { isOnline ->
                if (isOnline) {
                    githubApiService.getRepoByUrl(url)
                        .flatMap { repo ->
                            val repoDb = repo.dbModel
                            reposDao.insert(repoDb)
                            Single.just(repo)
                        }
                } else {
                    reposDao.getRepoByUrl(url)
                        .map { repoDb -> repoDb.coreModel }
                }
            }
    }
}