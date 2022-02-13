package com.rino.githubusers.core.cache

import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.model.GithubUserDetailed
import com.rino.githubusers.database.dao.UserDao
import com.rino.githubusers.network.GithubApiService
import com.rino.githubusers.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubUsersCacheImpl(
    private val networkStatus: NetworkStatus,
    private val githubApiService: GithubApiService,
    private val userDao: UserDao
) : GithubUsersCache {


    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.isOnlineSingle
            .flatMap { isOnline ->
                if (isOnline) {
                    githubApiService.getUsers()
                        .flatMap { users ->
                            val usersDb = users.map { it.dbModel }
                            userDao.insertAll(usersDb)
                            Single.just(users)
                        }
                } else {
                    userDao.getAllUsers()
                        .map { usersDb -> usersDb.map { it.coreModel } }
                }
            }
    }

    override fun getUserByLogin(login: String): Single<GithubUserDetailed> {
        return networkStatus.isOnlineSingle
            .flatMap { isOnline ->
                if (isOnline) {
                    githubApiService.getUserByLogin(login)
                        .flatMap { user ->
                            val userDb = user.dbModel
                            userDao.insert(userDb)
                            Single.just(user)
                        }
                } else {
                    userDao.getUserByLogin(login)
                        .map { userDb -> userDb.detailedCoreModel }
                }
            }
    }
}