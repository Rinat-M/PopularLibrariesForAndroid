package com.rino.githubusers.di.modules

import com.rino.githubusers.core.cache.GithubUsersCache
import com.rino.githubusers.core.cache.GithubUsersCacheImpl
import com.rino.githubusers.core.repository.GithubUsersRepository
import com.rino.githubusers.core.repository.GithubUsersRepositoryImpl
import com.rino.githubusers.di.scopes.UsersScope
import dagger.Binds
import dagger.Module

@Module
interface UsersModule {

    @Binds
    @UsersScope
    fun providesUsersRepository(impl: GithubUsersRepositoryImpl): GithubUsersRepository

    @Binds
    @UsersScope
    fun providesUsersCache(impl: GithubUsersCacheImpl): GithubUsersCache

}