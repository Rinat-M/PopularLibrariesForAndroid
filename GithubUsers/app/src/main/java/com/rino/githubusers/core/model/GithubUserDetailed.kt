package com.rino.githubusers.core.model

import com.rino.githubusers.database.entity.GithubUserEntity
import java.util.*

data class GithubUserDetailed(
    val id: Long,
    val login: String,
    val reposUrl: String,
    val publicRepos: Long,
    val followers: Long,
    val following: Long,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val avatarUrl: String? = null,
    val url: String? = null,
    val htmlUrl: String? = null,
    val name: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null
) {
    val dbModel
        get() = GithubUserEntity(
            id = id,
            login = login,
            reposUrl = reposUrl,
            avatarUrl = avatarUrl,
            publicRepos = publicRepos,
            followers = followers,
            following = following,
            name = name,
            location = location,
            createdAt = createdAt
        )
}