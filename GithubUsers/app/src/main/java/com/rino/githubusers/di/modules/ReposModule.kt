package com.rino.githubusers.di.modules

import com.rino.githubusers.core.cache.GithubReposCache
import com.rino.githubusers.core.cache.GithubReposCacheImpl
import com.rino.githubusers.core.repository.GithubReposRepository
import com.rino.githubusers.core.repository.GithubReposRepositoryImpl
import com.rino.githubusers.di.scopes.ReposScope
import dagger.Binds
import dagger.Module

@Module
interface ReposModule {

    @Binds
    @ReposScope
    fun providesReposRepository(impl: GithubReposRepositoryImpl): GithubReposRepository

    @Binds
    @ReposScope
    fun providesReposCache(impl: GithubReposCacheImpl): GithubReposCache

}