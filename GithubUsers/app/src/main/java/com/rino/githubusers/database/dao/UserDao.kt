package com.rino.githubusers.database.dao

import androidx.room.*
import com.rino.githubusers.database.entity.GithubUserEntity
import com.rino.githubusers.database.entity.GithubUserMainInfoEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<GithubUserEntity>)

    @Insert(entity = GithubUserEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertUsersMainInfoAll(users: List<GithubUserMainInfoEntity>): List<Long>

    @Update(entity = GithubUserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateUsersMainInfoAll(users: List<GithubUserMainInfoEntity>)

    @Query("SELECT * FROM GithubUserEntity")
    fun getAllUsers(): Single<List<GithubUserEntity>>

    @Query("SELECT * FROM GithubUserEntity WHERE login = :login")
    fun getUserByLogin(login: String): Single<GithubUserEntity>

    @Transaction
    fun insertOrUpdateUsersMainInfoAll(users: List<GithubUserMainInfoEntity>) {
        val insertResult = insertUsersMainInfoAll(users)
        val updateList = mutableListOf<GithubUserMainInfoEntity>()

        insertResult.forEachIndexed { index, result ->
            if (result == -1L) updateList.add(users[index])
        }

        if (updateList.isNotEmpty()) updateUsersMainInfoAll(updateList)
    }

}