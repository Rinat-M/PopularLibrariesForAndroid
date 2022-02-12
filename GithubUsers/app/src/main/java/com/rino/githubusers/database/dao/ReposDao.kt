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
    fun insert(user: GithubRepoEntity)

    @Query("SELECT * FROM GithubRepoEntity WHERE userId = :userId")
    fun getReposByUserId(userId: Long): Single<List<GithubRepoEntity>>

}