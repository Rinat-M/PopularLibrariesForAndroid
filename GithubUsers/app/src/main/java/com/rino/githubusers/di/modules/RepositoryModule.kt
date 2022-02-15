package com.rino.githubusers.di.modules

import com.rino.githubusers.core.repository.GithubReposRepository
import com.rino.githubusers.core.repository.GithubReposRepositoryImpl
import com.rino.githubusers.core.repository.GithubUsersRepository
import com.rino.githubusers.core.repository.GithubUsersRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun providesUsersRepository(impl: GithubUsersRepositoryImpl): GithubUsersRepository

    @Binds
    @Singleton
    fun providesReposRepository(impl: GithubReposRepositoryImpl): GithubReposRepository

}