package com.rino.githubusers.network

import com.rino.githubusers.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}