package com.rino.githubusers.di.modules

import com.rino.githubusers.core.cache.GithubReposCache
import com.rino.githubusers.core.cache.GithubUsersCache
import com.rino.githubusers.core.repository.GithubReposRepository
import com.rino.githubusers.core.repository.GithubReposRepositoryImpl
import com.rino.githubusers.core.repository.GithubUsersRepository
import com.rino.githubusers.core.repository.GithubUsersRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUsersRepository(githubUsersCache: GithubUsersCache): GithubUsersRepository {
        return GithubUsersRepositoryImpl(githubUsersCache)
    }

    @Provides
    @Singleton
    fun providesReposRepository(githubReposCache: GithubReposCache): GithubReposRepository {
        return GithubReposRepositoryImpl(githubReposCache)
    }

}