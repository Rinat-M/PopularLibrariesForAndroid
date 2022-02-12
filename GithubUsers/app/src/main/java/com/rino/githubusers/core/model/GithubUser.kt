package com.rino.githubusers.core.model

data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String? = null
)

