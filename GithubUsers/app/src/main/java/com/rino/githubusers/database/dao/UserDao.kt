package com.rino.githubusers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rino.githubusers.database.entity.GithubUserEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUserEntity)

    @Query("SELECT * FROM GithubUserEntity")
    fun getAllUsers(): Single<List<GithubUserEntity>>

}