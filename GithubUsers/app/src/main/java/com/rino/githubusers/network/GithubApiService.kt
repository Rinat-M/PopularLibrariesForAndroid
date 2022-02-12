package com.rino.githubusers.network

import com.rino.githubusers.model.GithubRepos
import com.rino.githubusers.model.GithubUser
import com.rino.githubusers.model.GithubUserDetailed
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("/users/{login}")
    fun getUserByLogin(@Path("login") login: String): Single<GithubUserDetailed>

    @GET("/users/{login}/repos")
    fun getUserReposByLogin(@Path("login") login: String): Single<List<GithubRepos>>

    @GET
    fun getRepoByUrl(@Url url: String): Single<GithubRepos>

}