package com.rino.githubusers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rino.githubusers.database.converter.DateConverter
import com.rino.githubusers.database.dao.ReposDao
import com.rino.githubusers.database.dao.UserDao
import com.rino.githubusers.database.entity.CachedImage
import com.rino.githubusers.database.entity.GithubRepoEntity
import com.rino.githubusers.database.entity.GithubUserEntity

@Database(
    entities = [GithubUserEntity::class, GithubRepoEntity::class, CachedImage::class],
    version = 2,
)
@TypeConverters(DateConverter::class)
abstract class GithubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val reposDao: ReposDao

    companion object {
        private const val DB_NAME = "github.db"

        private val migrationFrom1To2 by lazy { MigrationFrom1To2() }

        private var instance: GithubDatabase? = null

        fun getInstance(context: Context): GithubDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME)
                    .addMigrations(migrationFrom1To2)
                    .build()
            }

            return instance!!
        }
    }
}