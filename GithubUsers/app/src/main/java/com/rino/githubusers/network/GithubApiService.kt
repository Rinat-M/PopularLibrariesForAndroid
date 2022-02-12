package com.rino.githubusers.network

import com.rino.githubusers.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("/users/{login}")
    fun getUserByLogin(@Path("login") login: String): Single<GithubUser>
}