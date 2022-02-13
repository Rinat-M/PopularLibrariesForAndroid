package com.rino.githubusers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rino.githubusers.database.entity.GithubRepoEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: GithubRepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<GithubRepoEntity>)

    @Query("SELECT * FROM GithubRepoEntity WHERE userId = :userId")
    fun getReposByUserId(userId: Long): Single<List<GithubRepoEntity>>

    @Query("""
        SELECT repos.* 
        FROM GithubRepoEntity AS repos 
        INNER JOIN GithubUserEntity AS users
            ON repos.userId = users.id
        WHERE users.login = :login""")
    fun getUserReposByLogin(login: String): Single<List<GithubRepoEntity>>

    @Query("SELECT * FROM GithubRepoEntity WHERE url = :url")
    fun getRepoByUrl(url: String): Single<GithubRepoEntity>

}