package com.rino.githubusers.core.model

import com.rino.githubusers.database.entity.GithubUserMainInfoEntity

data class GithubUser(
    val id: Long,
    val login: String,
    val reposUrl: String,
    val avatarUrl: String? = null
) {
    val dbModel get() = GithubUserMainInfoEntity(id, login, reposUrl, avatarUrl)
}

