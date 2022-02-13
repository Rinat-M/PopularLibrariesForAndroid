package com.rino.githubusers.core.model

import com.rino.githubusers.database.entity.GithubUserEntity

data class GithubUser(
    val id: Long,
    val login: String,
    val reposUrl: String,
    val avatarUrl: String? = null
) {
    val dbModel get() = GithubUserEntity(id, login, reposUrl, avatarUrl)
}

