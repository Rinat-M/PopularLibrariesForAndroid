package com.rino.githubusers.repository

import com.rino.githubusers.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserById(id: Long): Single<GithubUser>
}