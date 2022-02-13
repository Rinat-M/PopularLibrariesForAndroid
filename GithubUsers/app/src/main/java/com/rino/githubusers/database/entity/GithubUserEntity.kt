package com.rino.githubusers.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.model.GithubUserDetailed
import java.util.*

@Entity
data class GithubUserEntity(
    @PrimaryKey val id: Long,
    val login: String,
    val reposUrl: String,
    val avatarUrl: String? = null,
    val publicRepos: Long = 0,
    val followers: Long = 0,
    val following: Long = 0,
    val name: String? = null,
    val location: String? = null,
    val createdAt: Date? = null
) {
    val coreModel get() = GithubUser(id, login, reposUrl, avatarUrl)

    val detailedCoreModel
        get() = GithubUserDetailed(
            id = id,
            login = login,
            reposUrl = reposUrl,
            publicRepos = publicRepos,
            followers = followers,
            following = following,
            createdAt = createdAt
        )
}