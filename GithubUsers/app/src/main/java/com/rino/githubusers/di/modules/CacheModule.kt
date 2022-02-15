package com.rino.githubusers.di.modules

import android.widget.ImageView
import com.rino.githubusers.core.cache.GithubReposCache
import com.rino.githubusers.core.cache.GithubReposCacheImpl
import com.rino.githubusers.core.cache.GithubUsersCache
import com.rino.githubusers.core.cache.GithubUsersCacheImpl
import com.rino.githubusers.database.dao.ImagesDao
import com.rino.githubusers.database.dao.ReposDao
import com.rino.githubusers.database.dao.UsersDao
import com.rino.githubusers.network.GithubApiService
import com.rino.githubusers.network.NetworkStatus
import com.rino.githubusers.ui.base.GlideImageCacheLoader
import com.rino.githubusers.ui.base.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun providesUsersCache(
        apiService: GithubApiService,
        usersDao: UsersDao,
        networkStatus: NetworkStatus
    ): GithubUsersCache {
        return GithubUsersCacheImpl(networkStatus, apiService, usersDao)
    }

    @Provides
    @Singleton
    fun providesReposCache(
        apiService: GithubApiService,
        reposDao: ReposDao,
        networkStatus: NetworkStatus
    ): GithubReposCache {
        return GithubReposCacheImpl(networkStatus, apiService, reposDao)
    }

    @Provides
    @Singleton
    fun providesGlideImageCacheLoader(imagesDao: ImagesDao): ImageLoader<ImageView> {
        return GlideImageCacheLoader(imagesDao)
    }

}