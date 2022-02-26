package com.rino.githubusers.di.modules

import android.content.Context
import com.rino.githubusers.database.GithubDatabase
import com.rino.githubusers.database.dao.ImagesDao
import com.rino.githubusers.database.dao.ReposDao
import com.rino.githubusers.database.dao.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): GithubDatabase {
        return GithubDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providesUsersDao(database: GithubDatabase): UsersDao {
        return database.usersDao
    }

    @Provides
    @Singleton
    fun providesReposDao(database: GithubDatabase): ReposDao {
        return database.reposDao
    }

    @Provides
    @Singleton
    fun providesImagesDao(database: GithubDatabase): ImagesDao {
        return database.imagesDao
    }

}