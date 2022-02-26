package com.rino.githubusers.core.cache

import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.model.GithubUserDetailed
import com.rino.githubusers.database.dao.UsersDao
import com.rino.githubusers.network.GithubApiService
import com.rino.githubusers.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubUsersCacheImpl(
    private val networkStatus: NetworkStatus,
    private val githubApiService: GithubApiService,
    private val usersDao: UsersDao
) : GithubUsersCache {


    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.isOnlineSingle
            .flatMap { isOnline ->
                if (isOnline) {
                    githubApiService.getUsers()
                        .flatMap { users ->
                            val usersDb = users.map { it.dbModel }
                            usersDao.insertOrUpdateUsersMainInfoAll(usersDb)
                            Single.just(users)
                        }
                } else {
                    usersDao.getAllUsers()
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
                            usersDao.insert(userDb)
                            Single.just(user)
                        }
                } else {
                    usersDao.getUserByLogin(login)
                        .map { userDb -> userDb.detailedCoreModel }
                }
            }
    }
}